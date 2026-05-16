package com.arenahub.backend.service;

import com.arenahub.backend.domain.Evento;
import com.arenahub.backend.domain.categoria.CategoriaEvento;
import com.arenahub.backend.domain.espaco.Espaco;
import com.arenahub.backend.domain.espaco.StatusEspaco;
import com.arenahub.backend.domain.espaco.TipoCobranca;
import com.arenahub.backend.domain.evento.ClassificacaoIndicativa;
import com.arenahub.backend.dto.EventoRequestDTO;
import com.arenahub.backend.dto.EventoResponseDTO;
import com.arenahub.backend.repository.CategoriaEventoRepository;
import com.arenahub.backend.repository.EspacoRepository;
import com.arenahub.backend.repository.EventoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import com.arenahub.backend.domain.Produtor;
import com.arenahub.backend.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
class EventoServiceTest {

    @Mock
    private EventoRepository eventoRepository;

    @Mock
    private CategoriaEventoRepository categoriaRepository;

    @Mock
    private EspacoRepository espacoRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private EventoService eventoService;

    private CategoriaEvento criarCategoria() {
        CategoriaEvento cat = new CategoriaEvento("Musica");
        cat.setId(1L);
        return cat;
    }

    private Espaco criarEspaco() {
        Espaco esp = new Espaco("Palco", 1000, "Centro", TipoCobranca.DIA, BigDecimal.valueOf(100), StatusEspaco.ATIVO);
        esp.setId(1L);
        return esp;
    }

    private EventoRequestDTO criarDto() {
        return new EventoRequestDTO(
                "Show de Rock",
                "Grande show de rock",
                LocalDateTime.of(2025, 6, 1, 20, 0),
                LocalDateTime.of(2025, 6, 1, 23, 0),
                500,
                ClassificacaoIndicativa.LIVRE,
                1L, // categoriaId
                1L // espacoId
        );
    }

    private Evento criarEvento() {
        Evento evento = new Evento(
                "Show de Rock",
                "Grande show de rock",
                LocalDateTime.of(2025, 6, 1, 20, 0),
                LocalDateTime.of(2025, 6, 1, 23, 0),
                500,
                ClassificacaoIndicativa.LIVRE,
                criarCategoria(),
                criarEspaco(),
                new Produtor("12345678901234"));
        evento.setId(1L);
        return evento;
    }

    @Test
    void deveRetornarEventosFiltradosPorCategoriaEData() {
        String categoria = "Musica";
        LocalDateTime data = LocalDateTime.of(2025, 6, 1, 20, 0);
        List<Evento> esperado = List.of(criarEvento());
        when(eventoRepository.findByCategoriaAndData(categoria, data)).thenReturn(esperado);

        List<EventoResponseDTO> resultado = eventoService.listarEventosFiltrados(categoria, data);

        assertEquals(1, resultado.size());
        assertEquals("Show de Rock", resultado.get(0).nome());
        verify(eventoRepository).findByCategoriaAndData(categoria, data);
        verify(eventoRepository, never()).findAll();
    }

    @Test
    void deveRetornarTodosEventosQuandoAmbosFiltrosNulos() {
        List<Evento> esperado = List.of(criarEvento());
        when(eventoRepository.findAll()).thenReturn(esperado);

        List<EventoResponseDTO> resultado = eventoService.listarEventosFiltrados(null, null);

        assertEquals(1, resultado.size());
        assertEquals("Show de Rock", resultado.get(0).nome());
        verify(eventoRepository).findAll();
        verify(eventoRepository, never()).findByCategoriaAndData(any(), any());
    }

    @Test
    void deveRetornarTodosEventosQuandoApenasCategoriaInformada() {
        List<Evento> esperado = List.of(criarEvento());
        when(eventoRepository.findAll()).thenReturn(esperado);

        List<EventoResponseDTO> resultado = eventoService.listarEventosFiltrados("Musica", null);

        assertEquals(1, resultado.size());
        verify(eventoRepository).findAll();
        verify(eventoRepository, never()).findByCategoriaAndData(any(), any());
    }

    @Test
    void deveRetornarTodosEventosQuandoApenasDataInformada() {
        List<Evento> esperado = List.of(criarEvento());
        when(eventoRepository.findAll()).thenReturn(esperado);

        List<EventoResponseDTO> resultado = eventoService.listarEventosFiltrados(null, LocalDateTime.of(2025, 6, 1, 20, 0));

        assertEquals(1, resultado.size());
        verify(eventoRepository).findAll();
        verify(eventoRepository, never()).findByCategoriaAndData(any(), any());
    }

    @Test
    void deveRetornarEventoQuandoIdExiste() {
        Evento evento = criarEvento();
        when(eventoRepository.findById(1L)).thenReturn(Optional.of(evento));

        Optional<Evento> resultado = eventoService.listarEventoPorId(1L);

        assertTrue(resultado.isPresent());
        assertEquals(evento, resultado.get());
        verify(eventoRepository).findById(1L);
    }

    @Test
    void deveRetornarVazioQuandoIdNaoExiste() {
        when(eventoRepository.findById(99L)).thenReturn(Optional.empty());

        Optional<Evento> resultado = eventoService.listarEventoPorId(99L);

        assertTrue(resultado.isEmpty());
        verify(eventoRepository).findById(99L);
    }

    @Test
    void deveCadastrarEventoComSucesso() {
        EventoRequestDTO dto = criarDto();
        Evento eventoSalvo = criarEvento();
        Produtor produtor = new Produtor("12345678901234");
        produtor.setEmail("produtor@test.com");

        Authentication authentication = mock(Authentication.class);
        SecurityContext securityContext = mock(SecurityContext.class);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.getName()).thenReturn("produtor@test.com");
        SecurityContextHolder.setContext(securityContext);

        when(userRepository.findByEmail("produtor@test.com")).thenReturn(Optional.of(produtor));
        when(categoriaRepository.findById(dto.categoriaId())).thenReturn(Optional.of(criarCategoria()));
        when(espacoRepository.findById(dto.espacoId())).thenReturn(Optional.of(criarEspaco()));
        when(eventoRepository.save(any(Evento.class))).thenReturn(eventoSalvo);

        EventoResponseDTO resultado = eventoService.cadastrarEvento(dto);

        assertNotNull(resultado);
        assertEquals("Show de Rock", resultado.nome());
        verify(eventoRepository).save(any(Evento.class));
    }

    @Test
    void deveCriarEventoComDadosCorretosDoDTOAoCadastrar() {
        EventoRequestDTO dto = criarDto();
        Produtor produtor = new Produtor("12345678901234");
        produtor.setEmail("produtor@test.com");

        Authentication authentication = mock(Authentication.class);
        SecurityContext securityContext = mock(SecurityContext.class);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.getName()).thenReturn("produtor@test.com");
        SecurityContextHolder.setContext(securityContext);

        when(userRepository.findByEmail("produtor@test.com")).thenReturn(Optional.of(produtor));
        when(categoriaRepository.findById(dto.categoriaId())).thenReturn(Optional.of(criarCategoria()));
        when(espacoRepository.findById(dto.espacoId())).thenReturn(Optional.of(criarEspaco()));

        when(eventoRepository.save(any(Evento.class))).thenAnswer(invocation -> invocation.getArgument(0));

        eventoService.cadastrarEvento(dto);

        ArgumentCaptor<Evento> captor = ArgumentCaptor.forClass(Evento.class);
        verify(eventoRepository).save(captor.capture());

        Evento capturado = captor.getValue();
        assertEquals("Show de Rock", capturado.getNome());
        assertEquals("Grande show de rock", capturado.getDescricao());
        assertEquals(dto.dataInicio(), capturado.getDataInicio());
        assertEquals(dto.dataFim(), capturado.getDataFim());
        assertEquals(500, capturado.getExpectativaPublico());
        assertEquals(ClassificacaoIndicativa.LIVRE, capturado.getClassificacaoIndicativa());
        assertEquals(1L, capturado.getCategoria().getId());
        assertEquals(1L, capturado.getEspaco().getId());
    }

    @Test
    void deveAtualizarEventoComSucesso() {
        Evento existente = criarEvento();
        EventoRequestDTO dto = new EventoRequestDTO(
                "Festival de Jazz",
                "Evento de jazz ao vivo",
                LocalDateTime.of(2025, 7, 10, 18, 0),
                LocalDateTime.of(2025, 7, 10, 22, 0),
                300,
                ClassificacaoIndicativa.DOZE,
                2L,
                2L);

        CategoriaEvento novaCategoria = new CategoriaEvento("Jazz");
        novaCategoria.setId(2L);
        Espaco novoEspaco = criarEspaco();
        novoEspaco.setId(2L);

        when(eventoRepository.findById(1L)).thenReturn(Optional.of(existente));
        when(categoriaRepository.findById(2L)).thenReturn(Optional.of(novaCategoria));
        when(espacoRepository.findById(2L)).thenReturn(Optional.of(novoEspaco));
        when(eventoRepository.save(existente)).thenReturn(existente);

        EventoResponseDTO resultado = eventoService.atualizarEvento(1L, dto);

        assertNotNull(resultado);
        assertEquals("Festival de Jazz", existente.getNome());
        assertEquals("Evento de jazz ao vivo", existente.getDescricao());
        assertEquals(LocalDateTime.of(2025, 7, 10, 18, 0), existente.getDataInicio());
        assertEquals(LocalDateTime.of(2025, 7, 10, 22, 0), existente.getDataFim());
        assertEquals(300, existente.getExpectativaPublico());
        assertEquals(ClassificacaoIndicativa.DOZE, existente.getClassificacaoIndicativa());
        assertEquals(2L, existente.getCategoria().getId());
        assertEquals(2L, existente.getEspaco().getId());

        verify(eventoRepository).findById(1L);
        verify(eventoRepository).save(existente);
    }

    @Test
    void deveLancarExcecaoAoAtualizarEventoInexistente() {
        EventoRequestDTO dto = criarDto();
        when(eventoRepository.findById(99L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> eventoService.atualizarEvento(99L, dto));

        assertNotNull(exception.getMessage());
        verify(eventoRepository).findById(99L);
        verify(eventoRepository, never()).save(any(Evento.class));
    }

    @Test
    void deveRemoverEventoComSucesso() {
        when(eventoRepository.existsById(1L)).thenReturn(true);

        eventoService.removerEvento(1L);

        verify(eventoRepository).existsById(1L);
        verify(eventoRepository).deleteById(1L);
    }

    @Test
    void deveLancarExcecaoAoRemoverEventoInexistente() {
        when(eventoRepository.existsById(99L)).thenReturn(false);

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class,
                () -> eventoService.removerEvento(99L));

        assertTrue(exception.getMessage().contains("99"));
        verify(eventoRepository).existsById(99L);
        verify(eventoRepository, never()).deleteById(any());
    }
}
