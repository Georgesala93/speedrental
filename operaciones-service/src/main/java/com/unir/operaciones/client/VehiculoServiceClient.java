package com.unir.operaciones.client;

import com.unir.operaciones.model.VehiculoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

// Usamos el nombre del servicio registrado en Eureka
@FeignClient(name = "vehiculos-service")
public interface VehiculoServiceClient {

    @GetMapping("/api/vehiculos/{id}")
    VehiculoDTO getVehiculoById(@PathVariable("id") Long id);

    @PutMapping("/api/vehiculos/{id}")
    void updateVehiculo(@PathVariable("id") Long id, @RequestBody VehiculoDTO vehiculo);
}
