package com.unir.operaciones.service;

import com.unir.operaciones.model.Alquiler;

/**
 * Interfaz del servicio de operaciones (lógica de negocio para alquileres).
 */
public interface OperacionesService {

    /**
     * Crea un nuevo alquiler aplicando la lógica de negocio necesaria.
     * @param alquiler datos del alquiler
     * @return entidad guardada
     */
    Alquiler crearAlquiler(Alquiler alquiler);
}

