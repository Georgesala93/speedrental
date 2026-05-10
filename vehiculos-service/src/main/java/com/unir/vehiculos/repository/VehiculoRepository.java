package com.unir.vehiculos.repository;

import com.unir.vehiculos.model.Estado;
import com.unir.vehiculos.model.Vehiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repositorio JPA para la entidad Vehiculo.
 * Incluye métodos de consulta por marca, modelo y estado para cumplir la rúbrica.
 */
@Repository
public interface VehiculoRepository extends JpaRepository<Vehiculo, Long> {

    // Buscar por marca exacta
    List<Vehiculo> findByMarca(String marca);

    // Buscar por modelo exacto
    List<Vehiculo> findByModelo(String modelo);

    // Buscar por estado (DISPONIBLE / NO_DISPONIBLE)
    List<Vehiculo> findByEstado(Estado estado);
}

