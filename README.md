# Proyecto Final IS2 — ServiCiudad

Este repositorio contiene el frontend (Angular), el backend (Spring Boot) y la base de datos (PostgreSQL) orquestados con Docker Compose para facilitar el desarrollo y las pruebas locales.

## 1) Cómo clonar el proyecto

Asegúrate de tener Git instalado. Luego, en una terminal ejecuta:

```bash
git clone https://github.com/paulomeister/final_is2.git
cd final_is2
```

> Nota: Si ya tienes el proyecto descargado con otro nombre de carpeta, puedes entrar a ese directorio y continuar con los pasos de ejecución.

## 2) Configuración automática de la base de datos

La base de datos se provisiona automáticamente al iniciar el contenedor de PostgreSQL por primera vez. Esto sucede gracias a que en `docker-compose.yaml` se monta el archivo `facturas_acueducto_init.sql` en la ruta especial `/docker-entrypoint-initdb.d/` del contenedor. El entrypoint de la imagen oficial de Postgres ejecuta cualquier script `.sql` o `.sh` colocado allí durante la inicialización.

- El archivo `facturas_acueducto_init.sql` contiene el DDL (creación de tablas) y los datos de prueba iniciales.
- Este proceso corre solo la primera vez que se crea el volumen de datos de Postgres. Si vuelves a levantar los contenedores con el mismo volumen, no se volverá a ejecutar el script (el volumen ya persiste los datos).
- Si deseas reprovisionar la base desde cero (volviendo a ejecutar el script), baja los contenedores y elimina el volumen con:

```bash
# Linux/macOS
make compose-down-remove

# Windows (usando mingw32-make)
mingw32-make compose-down-remove
```

O manualmente con Docker Compose:

```bash
docker compose down -v
```

## 3) Ejecutar el proyecto con Docker

### Prerrequisitos

- Debes contar con Docker instalado. Si no lo tienes, revisa la guía oficial “Get Started” aquí:
  https://www.docker.com/get-started/

### Puesta en marcha (Docker Compose)

Desde la carpeta raíz del proyecto, ejecuta:

```bash
docker compose up --build
```

Esto construirá y levantará los tres servicios definidos en `docker-compose.yaml`:

- frontend (Angular) expuesto en http://localhost:4200
- backend (Spring Boot) expuesto en http://localhost:8080
- db (PostgreSQL) expuesto en localhost:5432 (para uso local si lo necesitas)

> Consejo: El primer build puede tardar un poco por la instalación de dependencias y compilaciones.

### Uso del Makefile (opcional, facilita comandos)

Se incluye un `Makefile` con atajos para el ciclo de vida de los contenedores:

- Levantar:
  ```bash
  # Linux/macOS
  make compose-up

  # Windows (usando MinGW)
  mingw32-make compose-up
  ```

- Levantar (forzando build):
  ```bash
  # Linux/macOS
  make compose-up-build

  # Windows (usando MinGW)
  mingw32-make compose-up-build
  ```

- Bajar contenedores:
  ```bash
  # Linux/macOS
  make compose-down

  # Windows (usando MinGW)
  mingw32-make compose-down
  ```

- Bajar contenedores y eliminar volúmenes (reprovisionar DB desde cero):
  ```bash
  # Linux/macOS
  make compose-down-remove

  # Windows (usando MinGW)
  mingw32-make compose-down-remove
  ```

#### Nota para Windows

Si estás en Windows y no tienes `make`, existen varias opciones. Una de las más sencillas es instalar MinGW y usar `mingw32-make`:

- Descarga MinGW desde: https://sourceforge.net/projects/mingw/
- Asegúrate de agregar el binario a tu PATH para poder ejecutar `mingw32-make` desde cualquier terminal.

## 4) Variables de entorno

Es obligatorio contar con un archivo `.env` en el directorio raíz del proyecto antes de levantar los contenedores. Este archivo define las credenciales y parámetros de la base de datos que:

- Usa el contenedor de PostgreSQL durante la inicialización (usuario, contraseña, base de datos).
- Lee el backend (Spring Boot) para conectarse a la base de datos dentro de la red de Docker Compose.

Ejemplo de `.env` recomendado:

```dotenv
# PostgreSQL (leído por el contenedor oficial de Postgres)
POSTGRES_DB=serviciudad
POSTGRES_USER=serviuser
POSTGRES_PASSWORD=servipassword

# Conexión interna para el backend (leído por Spring)
DB_HOST=db
DB_PORT=5432
```

Notas:
- `DB_HOST` debe ser `db`, que es el nombre del servicio de base de datos definido en `docker-compose.yaml` y resoluble dentro de la red de Docker Compose.
- El backend usa estas variables en `application.yml`:
  - `spring.datasource.url=jdbc:postgresql://${DB_HOST:db}:${DB_PORT:5432}/${POSTGRES_DB:serviciudad}`
  - `spring.datasource.username=${POSTGRES_USER:serviuser}`
  - `spring.datasource.password=${POSTGRES_PASSWORD:servipassword}`
- Si ajustas los valores en `.env`, se aplicarán tanto al contenedor de Postgres (creación de la base y credenciales) como al backend para la conexión.

## 5) Endpoints de referencia

- Frontend (Angular): http://localhost:4200
- Backend (Spring): http://localhost:8080
  - Endpoint ejemplo de consulta: `GET /api/v1/clientes/{clienteId}/deuda-consolidada`

---