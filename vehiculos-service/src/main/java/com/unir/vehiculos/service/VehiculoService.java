package com.unir.vehiculos.service;

import com.unir.vehiculos.model.Estado;
import com.unir.vehiculos.model.Vehiculo;

import java.util.List;
import java.util.Optional;

public interface VehiculoService {

    List<Vehiculo> findAll();

    Optional<Vehiculo> findById(Long id);

    Vehiculo create(Vehiculo vehiculo);

    Optional<Vehiculo> update(Long id, Vehiculo vehiculo);

    boolean delete(Long id);

    List<Vehiculo> findByMarca(String marca);

    List<Vehiculo> findByModelo(String modelo);

    List<Vehiculo> findByEstado(Estado estado);
}
