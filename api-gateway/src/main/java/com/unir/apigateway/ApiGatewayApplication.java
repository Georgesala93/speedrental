package com.unir.apigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * Clase principal para el API Gateway.
 * Se anota con @EnableEurekaClient para registrarse en Eureka y poder resolver
 * nombres de servicio con lb://service-name desde Spring Cloud Gateway.
 *
 * Nota sobre rutas: en `application.yml` configuramos rutas que mantienen el prefijo
 * `/api/...` y NO aplicamos StripPrefix aquí. Por tanto los controladores de los
 * microservicios deben exponer endpoints con el prefijo `/api` (ej. `/api/vehiculos`).
 */
@SpringBootApplication
@EnableEurekaClient
public class ApiGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiGatewayApplication.class, args);
    }
}

