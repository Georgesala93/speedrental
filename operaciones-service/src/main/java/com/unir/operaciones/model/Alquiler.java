package com.unir.operaciones.model;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Entidad JPA que representa un alquiler de vehículo.
 */
@Entity
@Table(name = "alquileres")
public class Alquiler {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Identificador del usuario que realiza el alquiler (puede mapear a la entidad de usuarios en otro microservicio) */
    @Column(name = "user_id", nullable = false)
    private Long userId;

    /** Identificador del vehículo alquilado */
    @Column(name = "vehicle_id", nullable = false)
    private Long vehicleId;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RentalStatus status;

    public Alquiler() {
    }

    public Alquiler(Long id, Long userId, Long vehicleId, LocalDate startDate, LocalDate endDate, RentalStatus status) {
        this.id = id;
        this.userId = userId;
        this.vehicleId = vehicleId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public RentalStatus getStatus() {
        return status;
    }

    public void setStatus(RentalStatus status) {
        this.status = status;
    }
}

