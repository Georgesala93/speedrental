package com.unir.operaciones.service.impl;

import com.unir.operaciones.exception.VehicleNotAvailableException;
import com.unir.operaciones.model.Alquiler;
import com.unir.operaciones.model.RentalStatus;
import com.unir.operaciones.repository.AlquilerRepository;
import com.unir.operaciones.service.OperacionesService;
import org.springframework.stereotype.Service;

/**
 * Implementación simple del servicio de operaciones.
 * Actualmente sólo guarda el alquiler en la BD y marca el estado como ACTIVO.
 */
@Service
public class OperacionesServiceImpl implements OperacionesService {

    private final AlquilerRepository alquilerRepository;

    public OperacionesServiceImpl(AlquilerRepository alquilerRepository) {
        this.alquilerRepository = alquilerRepository;
    }

    @Override
    public Alquiler crearAlquiler(Alquiler alquiler) {
        // En una implementación real se verificaría si el vehículo está disponible
        // consultando al servicio de vehículos; aquí asumimos que siempre lo está.
        if (alquiler.getVehicleId() == null) {
            throw new VehicleNotAvailableException("Vehicle ID is required");
        }

        if (alquiler.getStatus() == null) {
            alquiler.setStatus(RentalStatus.ACTIVO);
        }

        return alquilerRepository.save(alquiler);
    }
}

