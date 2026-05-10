package com.unir.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Aplicación principal del servidor Eureka.
 *
 * Se anota con @EnableEurekaServer para habilitar el servidor de descubrimiento.
 * La configuración (register-with-eureka, fetch-registry) se gestiona desde application.yml
 * para evitar que el propio servidor se registre en sí mismo.
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaServerApplication.class, args);
    }
}

