package com.unir.operaciones.controller;

import com.unir.operaciones.model.Alquiler;
import com.unir.operaciones.service.OperacionesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

/**
 * Controlador REST para gestionar operaciones de alquiler.
 * Expone endpoint para crear alquileres que aplica la lógica de negocio del servicio.
 */
@RestController
@RequestMapping("/api/operaciones")
@Tag(name = "Operaciones", description = "Gestión de alquileres")
public class OperacionesController {

    private final OperacionesService operacionesService;

    public OperacionesController(OperacionesService operacionesService) {
        this.operacionesService = operacionesService;
    }

    @PostMapping("/alquilar")
    @Operation(summary = "Crear un nuevo alquiler")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Alquiler creado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Alquiler.class))),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida", content = @Content),
            @ApiResponse(responseCode = "409", description = "Vehículo no disponible", content = @Content),
            @ApiResponse(responseCode = "500", description = "Error interno", content = @Content)
    })
    public ResponseEntity<Alquiler> crearAlquiler(@Valid @RequestBody Alquiler alquiler) {
        // Delegar la lógica de negocio al servicio de operaciones
        Alquiler saved = operacionesService.crearAlquiler(alquiler);

        // Construir Location header apuntando al recurso creado
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(saved.getId()).toUri();

        return ResponseEntity.created(location).body(saved);
    }
}

