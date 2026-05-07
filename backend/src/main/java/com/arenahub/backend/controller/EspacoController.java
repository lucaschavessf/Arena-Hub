package com.arenahub.backend.controller;

import com.arenahub.backend.domain.espaco.Espaco;
import com.arenahub.backend.service.EspacoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/espaco")
@CrossOrigin(origins = "*")
public class EspacoController {

    private final EspacoService service;

    public EspacoController(EspacoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Espaco>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Espaco> getById(@PathVariable Long id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Espaco> create(@RequestBody Espaco espaco) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(espaco));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Espaco> update(@PathVariable Long id, @RequestBody Espaco espaco) {
        try {
            return ResponseEntity.ok(service.update(id, espaco));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
