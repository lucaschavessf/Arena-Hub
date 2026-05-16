package com.arenahub.backend.controller;

import com.arenahub.backend.dto.CategoriaIngressoDTO;
import com.arenahub.backend.repository.CategoriaIngressoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/categorias-ingresso")
public class CategoriaIngressoController {

    private final CategoriaIngressoRepository repository;

    public CategoriaIngressoController(CategoriaIngressoRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public ResponseEntity<List<CategoriaIngressoDTO>> getCategorias() {
        List<CategoriaIngressoDTO> categorias = repository.findAll().stream()
                .map(c -> new CategoriaIngressoDTO(c.getId(), c.getNome()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(categorias);
    }
}
