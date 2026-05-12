package com.unir.operaciones.model;

/**
 * Estado del vehículo: DISPONIBLE o NO_DISPONIBLE.
 * Este enum debe ser idéntico al de vehiculos-service para la comunicación Feign.
 */
public enum Estado {
    DISPONIBLE,
    NO_DISPONIBLE
}
