# Monitoreo local y en EC2

Compose agrega Prometheus, Blackbox Exporter (pruebas HTTP), Node Exporter (host) y métricas Micrometer del API. No se configuran credenciales en estos archivos. `.env` permanece local y no se debe versionar.

## 1. Preparar y validar configuración

Desde PowerShell, en la raíz del proyecto:

```powershell
if (-not (Test-Path .env)) { Copy-Item .env.example .env }
# Edita .env y cambia las contraseñas de ejemplo por valores únicos.
docker compose config --quiet
```

Si `.env` ya existe, no lo sobrescribas: edítalo conservando sus valores. Debes ver el comando terminar sin error antes de avanzar.
Si un puerto local ya está ocupado, cambia `FRONTEND_HOST_PORT`, `API_HOST_PORT` o `PROMETHEUS_HOST_PORT` en `.env`; son puertos del host, no los puertos internos entre servicios.

## 2. Levantar el stack

```powershell
docker compose up -d --build
docker compose ps
docker compose logs --tail 100 api prometheus blackbox-exporter node-exporter
```

Espera a que `mariadb`, `api`, `frontend`, `prometheus`, `blackbox-exporter` y `node-exporter` estén `Up` (MariaDB debe estar `healthy`). Comprueba:

- Aplicación: <http://localhost:8080/>
- Métricas API (solo dentro de Compose): `http://api:8082/actuator/prometheus`. Para validarlas localmente, usa `docker compose exec prometheus wget -qO- http://api:8082/actuator/prometheus`. No publiques el puerto 8082 en EC2.
- Prometheus: <http://localhost:9090/targets>
- Alertas: <http://localhost:9090/alerts>

En `/targets`, espera `State: UP` en `prometheus`, `backend`, `node-exporter`, `database-probe` y las dos URL de `http-probes`. También puedes consultar `probe_success` (debe dar `1`), `up` y `jvm_memory_used_bytes` en `/graph`. `database-probe` verifica conexión TCP con MariaDB; no exporta métricas internas de consultas o InnoDB.

> Node Exporter lee `/proc`, `/sys` y `/` del host Linux. En Docker Desktop para Windows, estas métricas corresponden al entorno Linux de Docker Desktop, no a Windows. En EC2 Ubuntu/Linux sí representan el host.

## 3. Probar una alerta sin alterar el código

Detén temporalmente el frontend:

```powershell
docker compose stop frontend
```

En `/targets`, la prueba HTTP del frontend aparecerá `DOWN`; después de 1 minuto, `ApplicationHttpProbeFailed` debe aparecer como `Firing` en `/alerts`. Restaura el servicio:

```powershell
docker compose start frontend
```

Espera a que el target vuelva a `UP` y la alerta desaparezca (puede tardar otro minuto). Comprueba también que las reglas cargaron sin errores en `/rules`. Para probar las otras alertas, detén temporalmente `api` y verifica `BackendMetricsTargetDown`, o detén `mariadb` y verifica `DatabaseTcpProbeFailed`; en cada caso restaura el servicio con `docker compose start <servicio>`.

Estas reglas se ven en Prometheus, pero por sí solas no envían correo, SMS ni notificaciones. Para eso se requiere Alertmanager y un canal de notificación configurado.

## 4. Evidencias antes del push

Guarda capturas (sin mostrar `.env` ni secretos) de:

1. `docker compose ps` con todos los servicios activos.
2. Prometheus `/targets` con targets en `UP`.
3. Prometheus `/graph` consultando `probe_success` y `jvm_memory_used_bytes`.
4. `/rules` sin errores y `/alerts` mostrando la alerta `Firing` durante la prueba controlada; opcionalmente otra captura cuando se resuelva.
5. Salida de `docker compose config --quiet` completada correctamente.

No agregues capturas que muestren contraseñas, tokens ni contenido de `.env`.

## 5. RAM y despliegue EC2

Prometheus queda limitado a 160 MiB, Blackbox Exporter a 32 MiB y Node Exporter a 32 MiB; Prometheus conserva como máximo 7 días/256 MB de series. El scrape de 30 segundos y estas retenciones reducen la carga. No se limita aquí el uso de MariaDB ni de las dos JVM:  más servicios en una t3.micro de 1 GB. El sistema operativo, Docker, MariaDB y las dos aplicaciones compiten por esa misma RAM; si hay presión de memoria u OOM, usa una instancia de al menos 2 GB o reduce memoria/heap de las aplicaciones antes de producción.

En EC2, sube al repositorio `Ejercicio5MyAPIGestor/observabilidad/`, el `pom.xml`, `application.properties`, `docker-compose.yml` y `.env.example` modificados. En la VM conserva su `.env` seguro (Git lo ignora). Desde el clon existente, ejecuta después del push:

```bash
cd ~/admin-proyectos
git pull --ff-only
docker compose config --quiet
docker compose up -d --build
docker compose ps
```

Usa exactamente los nombres de servicio de Compose: `api:8082`, `frontend:8080`, `node-exporter:9100`. Son nombres/puertos internos de la red Compose y no cambian por pasar a EC2. Prometheus solo publica `127.0.0.1:9090`: no abras 9090, 8082, 9100, 9115 ni 3306 en el Security Group. Accede a Prometheus por túnel SSH desde tu PC:

```powershell
ssh -i "C:\ruta\a\llave.pemide el consumo real con `docker stats` y evita habilitarm" -L 9090:127.0.0.1:9090 ubuntu@IP_PUBLICA_EC2
```

Mientras la sesión SSH esté abierta, usa <http://localhost:9090>. Conserva el `.env` propio de la VM; no es necesario cambiar `API_BASE_URL=http://api:8081` ni publicar el endpoint de gestión.

Al terminar las pruebas locales, usa `docker compose down` para detener y quitar contenedores y red; **no** agregues `-v` si quieres conservar los datos de MariaDB y Prometheus.
