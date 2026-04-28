package com.arenahub.backend.controller;

import com.arenahub.backend.dto.PedidoRequestDTO;
import com.arenahub.backend.dto.PedidoResponseDTO;
import com.arenahub.backend.service.PedidoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private final PedidoService _pedidoService;

    public PedidoController(PedidoService _pedidoService) {
        this._pedidoService = _pedidoService;
    }

    @PostMapping
    public ResponseEntity<PedidoResponseDTO> realizarCompra(
            @Valid @RequestBody PedidoRequestDTO dto) {
        PedidoResponseDTO response = _pedidoService.realizarCompra(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoResponseDTO> getPedido(
            @PathVariable Long id) {
        PedidoResponseDTO response = _pedidoService.buscar(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<List<PedidoResponseDTO>> getPedidosPorCliente(
            @PathVariable String clienteId) {
        List<PedidoResponseDTO> response = _pedidoService.buscarPorCliente(clienteId);
        return ResponseEntity.ok(response);
    }
}