package com.unir.operaciones.service.impl;

import com.unir.operaciones.client.VehiculoServiceClient;
import com.unir.operaciones.exception.VehicleNotAvailableException;
import com.unir.operaciones.model.Alquiler;
import com.unir.operaciones.model.Estado;
import com.unir.operaciones.model.RentalStatus;
import com.unir.operaciones.model.VehiculoDTO;
import com.unir.operaciones.repository.AlquilerRepository;
import com.unir.operaciones.service.OperacionesService;
import org.springframework.stereotype.Service;

@Service
public class OperacionesServiceImpl implements OperacionesService {

    private final AlquilerRepository alquilerRepository;
    private final VehiculoServiceClient vehiculoServiceClient;

    public OperacionesServiceImpl(AlquilerRepository alquilerRepository, VehiculoServiceClient vehiculoServiceClient) {
        this.alquilerRepository = alquilerRepository;
        this.vehiculoServiceClient = vehiculoServiceClient;
    }

    @Override
    public Alquiler crearAlquiler(Alquiler alquiler) {
        if (alquiler.getVehicleId() == null) {
            throw new VehicleNotAvailableException("Vehicle ID is required");
        }

        // 1. Obtener el estado actual del vehículo
        VehiculoDTO vehiculo = vehiculoServiceClient.getVehiculoById(alquiler.getVehicleId());

        // 2. Verificar si el vehículo está disponible
        if (vehiculo == null || !Estado.DISPONIBLE.name().equalsIgnoreCase(vehiculo.getEstado())) {
            throw new VehicleNotAvailableException("El vehículo no está disponible para alquiler.");
        }

        // 3. Guardar el nuevo alquiler en la base de datos
        if (alquiler.getStatus() == null) {
            alquiler.setStatus(RentalStatus.ACTIVO);
        }
        Alquiler nuevoAlquiler = alquilerRepository.save(alquiler);

        // 4. Actualizar el estado del vehículo a NO_DISPONIBLE
        vehiculo.setEstado(Estado.NO_DISPONIBLE.name());
        vehiculoServiceClient.updateVehiculo(vehiculo.getId(), vehiculo);

        return nuevoAlquiler;
    }
}
