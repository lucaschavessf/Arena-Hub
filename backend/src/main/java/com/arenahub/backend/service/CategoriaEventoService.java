package com.arenahub.backend.service;

import com.arenahub.backend.domain.categoria.CategoriaEvento;
import com.arenahub.backend.repository.CategoriaEventoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaEventoService {

    private final CategoriaEventoRepository repository;

    public CategoriaEventoService(CategoriaEventoRepository repository) {
        this.repository = repository;
    }

    public List<CategoriaEvento> findAll() {
        return repository.findAll();
    }

    public Optional<CategoriaEvento> findById(Long id) {
        return repository.findById(id);
    }

    public CategoriaEvento create(CategoriaEvento categoriaEvento) {
        return repository.save(categoriaEvento);
    }

    public CategoriaEvento update(Long id, CategoriaEvento updatedCategoria) {
        return repository.findById(id).map(categoria -> {
            categoria.setNome(updatedCategoria.getNome());
            return repository.save(categoria);
        }).orElseThrow(() -> new RuntimeException("CategoriaEvento não encontrada com id: " + id));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
