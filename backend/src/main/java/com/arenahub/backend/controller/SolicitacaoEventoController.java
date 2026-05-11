package com.arenahub.backend.controller;

import com.arenahub.backend.dto.SolicitacaoEventoRequestDTO;
import com.arenahub.backend.dto.SolicitacaoEventoResponseDTO;
import com.arenahub.backend.service.SolicitacaoEventoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/solicitacoes-evento")
public class SolicitacaoEventoController {

    private final SolicitacaoEventoService solicitacaoEventoService;

    public SolicitacaoEventoController(SolicitacaoEventoService solicitacaoEventoService) {
        this.solicitacaoEventoService = solicitacaoEventoService;
    }

    @PostMapping
    public ResponseEntity<SolicitacaoEventoResponseDTO> criarSolicitacao(
            @Valid @RequestBody SolicitacaoEventoRequestDTO dto) {
        SolicitacaoEventoResponseDTO response = solicitacaoEventoService.criarSolicitacao(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<SolicitacaoEventoResponseDTO>> listarSolicitacoes() {
        return ResponseEntity.ok(solicitacaoEventoService.listarSolicitacoes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SolicitacaoEventoResponseDTO> buscarSolicitacaoPorId(@PathVariable Long id) {
        return ResponseEntity.ok(solicitacaoEventoService.buscarSolicitacaoPorId(id));
    }
}
