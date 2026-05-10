package com.unir.operaciones.exception;

/**
 * Excepción lanzada cuando se intenta reservar un vehículo que no está disponible.
 */
public class VehicleNotAvailableException extends RuntimeException {

    public VehicleNotAvailableException() {
        super();
    }

    public VehicleNotAvailableException(String message) {
        super(message);
    }

    public VehicleNotAvailableException(String message, Throwable cause) {
        super(message, cause);
    }
}

