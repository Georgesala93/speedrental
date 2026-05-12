package com.unir.vehiculos.controller;

import com.unir.vehiculos.model.Estado;
import com.unir.vehiculos.model.Vehiculo;
import com.unir.vehiculos.service.VehiculoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

/**
 * Controlador REST para la entidad Vehiculo.
 * Todas las rutas se exponen bajo /api/vehiculos para concordar con el API Gateway.
 */
@RestController
@RequestMapping("/api/vehiculos")
@Tag(name = "Vehículos", description = "Operaciones CRUD sobre el catálogo de vehículos")
public class VehiculoController {

    private final VehiculoService service;

    public VehiculoController(VehiculoService service) {
        this.service = service;
    }

    @GetMapping
    @Operation(summary = "Listar todos los vehículos")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Listado recuperado correctamente",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Vehiculo.class)))
    })
    public ResponseEntity<List<Vehiculo>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener vehículo por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Vehículo encontrado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Vehiculo.class))),
            @ApiResponse(responseCode = "404", description = "Vehículo no encontrado", content = @Content)
    })
    public ResponseEntity<Vehiculo> getById(@PathVariable Long id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Crear un nuevo vehículo")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Vehículo creado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Vehiculo.class))),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida", content = @Content)
    })
    public ResponseEntity<Vehiculo> create(@RequestBody Vehiculo vehiculo) {
        Vehiculo saved = service.create(vehiculo);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(saved.getId()).toUri();
        return ResponseEntity.created(location).body(saved);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un vehículo existente")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Vehículo actualizado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Vehiculo.class))),
            @ApiResponse(responseCode = "404", description = "Vehículo no encontrado", content = @Content)
    })
    public ResponseEntity<Vehiculo> update(@PathVariable Long id, @RequestBody Vehiculo vehiculo) {
        return service.update(id, vehiculo)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un vehículo")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Vehículo eliminado", content = @Content),
            @ApiResponse(responseCode = "404", description = "Vehículo no encontrado", content = @Content)
    })
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        boolean deleted = service.delete(id);
        return deleted ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    // Consultas adicionales requeridas por la rúbrica
    @GetMapping("/search/marca/{marca}")
    @Operation(summary = "Buscar vehículos por marca")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "Listado recuperado",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Vehiculo.class)))})
    public ResponseEntity<List<Vehiculo>> findByMarca(@PathVariable String marca) {
        return ResponseEntity.ok(service.findByMarca(marca));
    }

    @GetMapping("/search/modelo/{modelo}")
    @Operation(summary = "Buscar vehículos por modelo")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "Listado recuperado",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Vehiculo.class)))})
    public ResponseEntity<List<Vehiculo>> findByModelo(@PathVariable String modelo) {
        return ResponseEntity.ok(service.findByModelo(modelo));
    }

    @GetMapping("/search/estado/{estado}")
    @Operation(summary = "Buscar vehículos por estado")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "Listado recuperado",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Vehiculo.class)))})
    public ResponseEntity<List<Vehiculo>> findByEstado(@PathVariable Estado estado) {
        return ResponseEntity.ok(service.findByEstado(estado));
    }
}
