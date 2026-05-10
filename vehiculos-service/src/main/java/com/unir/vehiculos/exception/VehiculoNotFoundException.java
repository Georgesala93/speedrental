package com.unir.vehiculos.exception;

/**
 * Excepción lanzada cuando no se encuentra un vehículo.
 */
public class VehiculoNotFoundException extends RuntimeException {

    public VehiculoNotFoundException() {
        super();
    }

    public VehiculoNotFoundException(String message) {
        super(message);
    }
}

