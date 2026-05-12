package com.unir.vehiculos.service.impl;

import com.unir.vehiculos.model.Estado;
import com.unir.vehiculos.model.Vehiculo;
import com.unir.vehiculos.repository.VehiculoRepository;
import com.unir.vehiculos.service.VehiculoService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehiculoServiceImpl implements VehiculoService {

    private final VehiculoRepository repository;

    public VehiculoServiceImpl(VehiculoRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Vehiculo> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Vehiculo> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Vehiculo create(Vehiculo vehiculo) {
        // Ensure id is null so JPA will insert
        vehiculo.setId(null);
        return repository.save(vehiculo);
    }

    @Override
    public Optional<Vehiculo> update(Long id, Vehiculo vehiculo) {
        return repository.findById(id).map(existing -> {
            existing.setMarca(vehiculo.getMarca());
            existing.setModelo(vehiculo.getModelo());
            existing.setMatricula(vehiculo.getMatricula());
            existing.setPrecio(vehiculo.getPrecio());
            existing.setEstado(vehiculo.getEstado());
            return repository.save(existing);
        });
    }

    @Override
    public boolean delete(Long id) {
        return repository.findById(id).map(v -> {
            repository.deleteById(id);
            return true;
        }).orElse(false);
    }

    @Override
    public List<Vehiculo> findByMarca(String marca) {
        return repository.findByMarca(marca);
    }

    @Override
    public List<Vehiculo> findByModelo(String modelo) {
        return repository.findByModelo(modelo);
    }

    @Override
    public List<Vehiculo> findByEstado(Estado estado) {
        return repository.findByEstado(estado);
    }
}
