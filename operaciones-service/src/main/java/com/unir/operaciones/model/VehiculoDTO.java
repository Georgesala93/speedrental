package com.unir.operaciones.model;

import lombok.Data;

@Data
public class VehiculoDTO {
    private Long id;
    private String marca;
    private String modelo;
    private String matricula;
    private Double precio;
    private String estado;
}
