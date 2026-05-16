package com.arenahub.backend.controller;

import com.arenahub.backend.dto.LoteBlocoRequestDTO;
import com.arenahub.backend.dto.LoteBlocoResponseDTO;
import com.arenahub.backend.service.LoteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/eventos")
public class LoteController {

    private final LoteService loteService;

    public LoteController(LoteService loteService) {
        this.loteService = loteService;
    }

    @GetMapping("/{eventoId}/lotes")
    public ResponseEntity<List<LoteBlocoResponseDTO>> getLotes(@PathVariable Long eventoId) {
        return ResponseEntity.ok(loteService.listarLotesDoEvento(eventoId));
    }

    @PostMapping("/{eventoId}/lotes")
    public ResponseEntity<Void> salvarLote(@PathVariable Long eventoId, @RequestBody LoteBlocoRequestDTO dto) {
        loteService.salvarLoteBloco(eventoId, dto);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{eventoId}/lotes/{nome}/status")
    public ResponseEntity<Void> alterarStatus(@PathVariable Long eventoId, @PathVariable String nome, @RequestBody Map<String, String> body) {
        loteService.alterarStatusLoteBloco(eventoId, nome, body.get("status"));
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{eventoId}/lotes/{nome}")
    public ResponseEntity<Void> excluirLote(@PathVariable Long eventoId, @PathVariable String nome) {
        loteService.excluirLoteBloco(eventoId, nome);
        return ResponseEntity.ok().build();
    }
}
