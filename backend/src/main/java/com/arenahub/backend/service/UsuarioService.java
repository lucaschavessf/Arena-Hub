package com.arenahub.backend.service;

import com.arenahub.backend.dto.*;
import com.arenahub.backend.domain.usuario.User;
import com.arenahub.backend.domain.cliente.Cliente;
import com.arenahub.backend.domain.administrador.Administrador;
import com.arenahub.backend.repository.IUserRepository;

import org.springframework.stereotype.Service;


@Service
public class UsuarioService {

    private final IUserRepository _usuarioRepository;

    public UsuarioService(IUserRepository _usuarioRepository) {
        this._usuarioRepository = _usuarioRepository;
    }

    public UsuarioResponseDTO cadastrarAdmin(AdminRequestDTO dto) {
        Administrador admin = new Administrador();
        admin.setName(dto.getName());
        admin.setEmail(dto.getEmail());
        admin.setPassword(dto.getPassword());
        admin.setSetorDepartamento(dto.getSetorDepartamento());

        _usuarioRepository.inserir(admin);
        return toResponse(admin);
    }

    public UsuarioResponseDTO cadastrarCliente(ClienteRequestDTO dto){
        Cliente cliente = new Cliente();
        cliente.setName(dto.getName());
        cliente.setEmail(dto.getEmail());
        cliente.setPassword(dto.getPassword());
        cliente.setCpf(dto.getCpf());

        _usuarioRepository.inserir(cliente);
        return toResponse(cliente);
    }

    public UsuarioResponseDTO buscar(String id) {
        User usuario = _usuarioRepository.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado: " + id));
        return toResponse(usuario);
    }

    public UsuarioResponseDTO atualizar(String id, AtualizarUsuarioDTO dto){
        User usuario = _usuarioRepository.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado: " + id));

        usuario.setName(dto.getName());
        usuario.setEmail(dto.getEmail());
        usuario.setPassword(dto.getPassword());

        _usuarioRepository.atualizar(usuario);
        return toResponse(usuario);
    }

    public void excluir(String id){
        _usuarioRepository.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado: " + id));

        _usuarioRepository.deletar(id);
    }

    private UsuarioResponseDTO toResponse(User u) {

        String tipo = null;
        String setorDepartamento = null;
        String cpf = null;

        if (u instanceof Administrador a) {
            tipo = "ADMIN";
            setorDepartamento = a.getSetorDepartamento();
        } else if (u instanceof Cliente c) {
            tipo = "CLIENTE";
            cpf = c.getCpf();
        }

        return new UsuarioResponseDTO(
                u.getId(),
                u.getName(),
                u.getEmail(),
                tipo,
                setorDepartamento,
                cpf
        );
    }
}