package com.arenahub.backend.service;

import com.arenahub.backend.domain.Administrador;
import com.arenahub.backend.domain.Cliente;
import com.arenahub.backend.domain.User;
import com.arenahub.backend.dto.AdminRequestDTO;
import com.arenahub.backend.dto.AtualizarUsuarioDTO;
import com.arenahub.backend.dto.ClienteRequestDTO;
import com.arenahub.backend.dto.UsuarioResponseDTO;
import com.arenahub.backend.repository.IUserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UsuarioServiceTest {

    @Mock
    private IUserRepository usuarioRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UsuarioService usuarioService;

    @Test
    void deveCadastrarAdministradorComSucesso() {
        AdminRequestDTO dto = new AdminRequestDTO();
        dto.setName("Admin Teste");
        dto.setEmail("admin@teste.com");
        dto.setPassword("senha123");
        dto.setSetorDepartamento("TI");

        when(passwordEncoder.encode("senha123")).thenReturn("HASH_ADMIN");
        doAnswer(invocation -> {
            User user = invocation.getArgument(0);
            user.setId("admin-1");
            return null;
        }).when(usuarioRepository).inserir(any(User.class));

        UsuarioResponseDTO response = usuarioService.cadastrarAdmin(dto);

        assertEquals("admin-1", response.getId());
        assertEquals("Admin Teste", response.getNome());
        assertEquals("admin@teste.com", response.getEmail());
        assertEquals("ADMIN", response.getTipo());
        assertEquals("TI", response.getSetorDepartamento());
        assertNull(response.getCpf());
        verify(passwordEncoder).encode("senha123");
        verify(usuarioRepository).inserir(any(User.class));
    }

    @Test
    void deveCadastrarClienteComSucesso() {
        ClienteRequestDTO dto = new ClienteRequestDTO();
        dto.setName("Cliente Teste");
        dto.setEmail("cliente@teste.com");
        dto.setPassword("senha456");
        dto.setCpf("12345678901");

        when(passwordEncoder.encode("senha456")).thenReturn("HASH_CLIENTE");
        doAnswer(invocation -> {
            User user = invocation.getArgument(0);
            user.setId("cliente-1");
            return null;
        }).when(usuarioRepository).inserir(any(User.class));

        UsuarioResponseDTO response = usuarioService.cadastrarCliente(dto);

        assertEquals("cliente-1", response.getId());
        assertEquals("Cliente Teste", response.getNome());
        assertEquals("cliente@teste.com", response.getEmail());
        assertEquals("CLIENTE", response.getTipo());
        assertEquals("12345678901", response.getCpf());
        assertNull(response.getSetorDepartamento());
        verify(passwordEncoder).encode("senha456");
        verify(usuarioRepository).inserir(any(User.class));
    }

    @Test
    void deveSalvarSenhaComHashENaoTextoPuroNoCadastro() {
        AdminRequestDTO dto = new AdminRequestDTO();
        dto.setName("Admin Hash");
        dto.setEmail("hash@teste.com");
        dto.setPassword("senhaPura");
        dto.setSetorDepartamento("Financeiro");

        when(passwordEncoder.encode("senhaPura")).thenReturn("HASH_SEGURO");

        usuarioService.cadastrarAdmin(dto);

        ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);
        verify(usuarioRepository).inserir(captor.capture());

        User usuarioPersistido = captor.getValue();
        assertEquals("HASH_SEGURO", usuarioPersistido.getPassword());
        assertNotEquals("senhaPura", usuarioPersistido.getPassword());
    }

    @Test
    void deveAtualizarUsuarioComSucesso() {
        Administrador existente = new Administrador();
        existente.setId("admin-2");
        existente.setName("Admin Antigo");
        existente.setEmail("antigo@teste.com");
        existente.setPassword("HASH_ANTIGO");
        existente.setSetorDepartamento("Operacoes");

        AtualizarUsuarioDTO dto = new AtualizarUsuarioDTO();
        dto.setName("Admin Novo");
        dto.setEmail("novo@teste.com");
        dto.setPassword("novaSenha");

        when(usuarioRepository.buscarPorId("admin-2")).thenReturn(Optional.of(existente));
        when(passwordEncoder.encode("novaSenha")).thenReturn("HASH_NOVO");

        UsuarioResponseDTO response = usuarioService.atualizar("admin-2", dto);

        assertEquals("admin-2", response.getId());
        assertEquals("Admin Novo", response.getNome());
        assertEquals("novo@teste.com", response.getEmail());
        assertEquals("ADMIN", response.getTipo());
        assertEquals("Operacoes", response.getSetorDepartamento());
        assertEquals("HASH_NOVO", existente.getPassword());
        verify(passwordEncoder).encode("novaSenha");
        verify(usuarioRepository).buscarPorId("admin-2");
        verify(usuarioRepository).atualizar(existente);
    }

    @Test
    void deveLancarExcecaoAoAtualizarUsuarioInexistente() {
        AtualizarUsuarioDTO dto = new AtualizarUsuarioDTO();
        dto.setName("Qualquer");
        dto.setEmail("qualquer@teste.com");
        dto.setPassword("senha123");

        when(usuarioRepository.buscarPorId("nao-existe")).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> usuarioService.atualizar("nao-existe", dto));

        assertEquals("Usuário não encontrado: nao-existe", exception.getMessage());
        verify(usuarioRepository).buscarPorId("nao-existe");
        verify(passwordEncoder, never()).encode(anyString());
        verify(usuarioRepository, never()).atualizar(any(User.class));
    }

    @Test
    void deveRetornarDtoComDadosEsperadosAoBuscarCliente() {
        Cliente cliente = new Cliente();
        cliente.setId("cliente-22");
        cliente.setName("Cliente Mapeado");
        cliente.setEmail("mapeado@teste.com");
        cliente.setPassword("HASH");
        cliente.setCpf("98765432100");

        when(usuarioRepository.buscarPorId("cliente-22")).thenReturn(Optional.of(cliente));

        UsuarioResponseDTO response = usuarioService.buscar("cliente-22");

        assertEquals("cliente-22", response.getId());
        assertEquals("Cliente Mapeado", response.getNome());
        assertEquals("mapeado@teste.com", response.getEmail());
        assertEquals("CLIENTE", response.getTipo());
        assertEquals("98765432100", response.getCpf());
        assertNull(response.getSetorDepartamento());
        verify(usuarioRepository).buscarPorId("cliente-22");
    }

    @Test
    void deveLancarExcecaoAoBuscarUsuarioInexistente() {
        when(usuarioRepository.buscarPorId("id-inexistente")).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> usuarioService.buscar("id-inexistente"));

        assertTrue(exception.getMessage().contains("encontrado"));
        assertTrue(exception.getMessage().contains("id-inexistente"));
        verify(usuarioRepository).buscarPorId("id-inexistente");
    }

    @Test
    void deveExcluirUsuarioComSucesso() {
        User usuario = new User();
        usuario.setId("id-1");

        when(usuarioRepository.buscarPorId("id-1")).thenReturn(Optional.of(usuario));

        usuarioService.excluir("id-1");

        verify(usuarioRepository).buscarPorId("id-1");
        verify(usuarioRepository).deletar("id-1");
    }

    @Test
    void deveLancarExcecaoAoExcluirUsuarioInexistente() {
        when(usuarioRepository.buscarPorId("id-404")).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> usuarioService.excluir("id-404"));

        assertTrue(exception.getMessage().contains("encontrado"));
        assertTrue(exception.getMessage().contains("id-404"));
        verify(usuarioRepository).buscarPorId("id-404");
        verify(usuarioRepository, never()).deletar(anyString());
    }
}
