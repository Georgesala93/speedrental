package com.unir.operaciones.repository;

import com.unir.operaciones.model.Alquiler;
import com.unir.operaciones.model.RentalStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlquilerRepository extends JpaRepository<Alquiler, Long> {

    List<Alquiler> findByUserId(Long userId);

    List<Alquiler> findByVehicleId(Long vehicleId);

    List<Alquiler> findByStatus(RentalStatus status);

}

