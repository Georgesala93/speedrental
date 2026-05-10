# SpeedRental - Sistema de Alquiler de Vehículos

SpeedRental es una plataforma de microservicios diseñada para gestionar el alquiler de vehículos. Este proyecto demuestra una arquitectura moderna basada en Spring Boot y Spring Cloud, totalmente contenedorizada con Docker.

## Stack Tecnológico

- **Lenguaje:** Java 17
- **Framework Principal:** Spring Boot 3.x
- **Ecosistema de Microservicios:** Spring Cloud (Eureka, Gateway)
- **Persistencia:** Spring Data JPA con base de datos MySQL
- **Documentación de API:** Springdoc OpenAPI (Swagger 3)
- **Despliegue:** Docker y Docker Compose

## Arquitectura

El sistema sigue una arquitectura de microservicios, donde cada componente es un servicio independiente y desplegable:

- **`eureka-server`:** Actúa como el registro de servicios (Service Registry). Todos los demás microservicios se registran en Eureka, lo que permite el descubrimiento dinámico de servicios en la red.

- **`api-gateway`:** Es el punto de entrada único (Single Point of Entry) para todas las peticiones del cliente. Se encarga de:
    - Enrutar las peticiones a los microservicios correspondientes.
    - Centralizar la documentación de la API de todos los servicios a través de Swagger UI.
    - Servir como un firewall y una capa de seguridad.

- **`vehiculos-service`:** Microservicio encargado de la gestión del catálogo de vehículos. Proporciona operaciones CRUD para los vehículos.

- **`operaciones-service`:** Microservicio que maneja la lógica de negocio para las operaciones de alquiler y devolución de vehículos.

## Despliegue en Entorno Local

Para ejecutar todo el ecosistema en tu máquina local, asegúrate de tener instalado Docker y Docker Compose.

### Pasos de ejecución:

1. Clona este repositorio y sitúate en la carpeta raíz (donde se encuentra el archivo `docker-compose.yml`).

2. Ejecuta el siguiente comando en tu terminal para compilar las imágenes y levantar los contenedores en segundo plano:
```bash
docker-compose up -d --build
```

3. El sistema levantará 5 contenedores (incluyendo la base de datos MySQL). Dale unos 30 a 60 segundos a Eureka para que registre todos los servicios.

## Verificación de Servicios

- **Dashboard de Eureka:** `http://localhost:8761`
- **Documentación de API (Swagger UI):** La documentación técnica de las APIs REST se expone de manera centralizada en el API Gateway y también de forma independiente en cada microservicio.
    - **Documentación Centralizada:** `http://localhost:8080/webjars/swagger-ui/index.html?urls.primaryName=vehiculos-service`
    - **API de Vehículos (Individual):** `http://localhost:8081/swagger-ui/index.html`
    - **API de Operaciones (Individual):** `http://localhost:8082/swagger-ui/index.html`
