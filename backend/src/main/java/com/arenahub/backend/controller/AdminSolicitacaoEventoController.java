package com.arenahub.backend.controller;

import com.arenahub.backend.dto.AprovarSolicitacaoEventoResponseDTO;
import com.arenahub.backend.dto.RejeitarSolicitacaoEventoRequestDTO;
import com.arenahub.backend.dto.SolicitacaoEventoResponseDTO;
import com.arenahub.backend.service.SolicitacaoEventoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/solicitacoes-evento")
public class AdminSolicitacaoEventoController {

    private final SolicitacaoEventoService solicitacaoEventoService;

    public AdminSolicitacaoEventoController(SolicitacaoEventoService solicitacaoEventoService) {
        this.solicitacaoEventoService = solicitacaoEventoService;
    }

    @PostMapping("/{id}/aprovar")
    public ResponseEntity<AprovarSolicitacaoEventoResponseDTO> aprovarSolicitacao(@PathVariable Long id) {
        return ResponseEntity.ok(solicitacaoEventoService.aprovarSolicitacao(id));
    }

    @PostMapping("/{id}/rejeitar")
    public ResponseEntity<SolicitacaoEventoResponseDTO> rejeitarSolicitacao(
            @PathVariable Long id,
            @Valid @RequestBody RejeitarSolicitacaoEventoRequestDTO dto) {
        return ResponseEntity.ok(solicitacaoEventoService.rejeitarSolicitacao(id, dto));
    }
}
