package com.arenahub.backend.service;

import com.arenahub.backend.domain.espaco.Espaco;
import com.arenahub.backend.repository.EspacoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EspacoService {

    private final EspacoRepository repository;

    public EspacoService(EspacoRepository repository) {
        this.repository = repository;
    }

    public List<Espaco> findAll() {
        return repository.findAll();
    }

    public Optional<Espaco> findById(Long id) {
        return repository.findById(id);
    }

    public Espaco create(Espaco espaco) {
        return repository.save(espaco);
    }

    public Espaco update(Long id, Espaco updatedEspaco) {
        return repository.findById(id).map(espaco -> {
            espaco.setNome(updatedEspaco.getNome());
            espaco.setCapacidade(updatedEspaco.getCapacidade());
            espaco.setLocalizacao(updatedEspaco.getLocalizacao());
            espaco.setTipoCobranca(updatedEspaco.getTipoCobranca());
            espaco.setPreco(updatedEspaco.getPreco());
            espaco.setStatus(updatedEspaco.getStatus());
            return repository.save(espaco);
        }).orElseThrow(() -> new RuntimeException("Espaco não encontrado com id: " + id));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
