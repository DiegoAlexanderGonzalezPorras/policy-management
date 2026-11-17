## Requisitos previos

Antes de comenzar, asegúrate de tener instalado:

- **Java JDK**: 25.
- **Docker Desktop** o Docker Engine.
- **Docker Compose** (normalmente incluido en Docker Desktop).

Comprueba las instalaciones:

```bash
java -version
docker --version
docker compose version
```

Instalar o usar maven:

```bash
./mvnw -version
```

En Windows:

```powershell
.\mvnw.cmd -version
```

## Levantar la base de datos con Docker Compose

Abre una terminal en la raíz del proyecto y ejecuta:

```bash
docker compose -f docker/docker-compose.yml up -d
```

Este comando crea e inicia los contenedores definidos en el archivo Compose en segundo plano.

## Ejecutar Spring Boot localmente

La aplicación se ejecuta fuera de Docker, directamente desde el equipo local.

En Linux o macOS:

```bash
./mvnw spring-boot:run
```

En Windows:

```powershell
.\mvnw.cmd spring-boot:run
```

## Detener los servicios

Para detener y eliminar los contenedores de la base de datos:

```bash
docker compose -f docker/docker-compose.yml down
```

Para detener los contenedores y eliminar también los volúmenes (esto puede borrar los datos persistidos de la base de datos):

```bash
docker compose -f docker/docker-compose.yml down -v
```

## Documentación de API - Swagger

El proyecto utiliza **Swagger UI (Springdoc OpenAPI)** para generar y visualizar automáticamente la documentación de los servicios REST.

### Acceso a Swagger

Para consultar la documentación de los endpoints, primero debes iniciar la aplicación utilizando el perfil Maven `dev`:

```bash
./mvnw spring-boot:run -Pdev
```

En Windows:

```powershell
.\mvnw.cmd spring-boot:run -Pdev
```

Una vez iniciada la aplicación, puedes acceder a los siguientes recursos:

| Recurso | URL |
|---|---|
| Swagger UI | http://localhost:8080/swagger-ui/index.html |
