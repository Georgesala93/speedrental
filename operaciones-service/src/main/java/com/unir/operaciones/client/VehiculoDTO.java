package com.unir.operaciones.client;

import java.math.BigDecimal;

/**
 * DTO usado por Feign para comunicarse con vehiculos-service.
 * Refleja los campos necesarios para leer y actualizar el estado del vehículo.
 */
public class VehiculoDTO {

    private Long id;
    private String marca;
    private String modelo;
    private String matricula;
    private BigDecimal precio;
    private String estado;

    public VehiculoDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}

