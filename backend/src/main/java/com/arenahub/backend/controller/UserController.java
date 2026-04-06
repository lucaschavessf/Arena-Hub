package com.arenahub.backend.controller;


import com.arenahub.backend.dto.*;
import com.arenahub.backend.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UsuarioService _usuarioService;

    @PostMapping("/admin")
    public ResponseEntity<UsuarioResponseDTO> postAdmin(
            @Valid @RequestBody AdminRequestDTO dto) {
        UsuarioResponseDTO response = _usuarioService.cadastrarAdmin(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/cliente")
    public ResponseEntity<UsuarioResponseDTO> postCliente(
            @Valid @RequestBody ClienteRequestDTO dto) {
        UsuarioResponseDTO response = _usuarioService.cadastrarCliente(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> getUsuario(
            @PathVariable String id) {
        UsuarioResponseDTO response = _usuarioService.buscar(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> putUsuario(
            @PathVariable String id,
            @Valid @RequestBody AtualizarUsuarioDTO dto) {
        UsuarioResponseDTO response = _usuarioService.atualizar(id, dto);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUsuario(
            @PathVariable String id) {
        _usuarioService.excluir(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<String> getUser(){
        return ResponseEntity.ok("sucesso!");
    }

}