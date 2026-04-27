package com.arenahub.backend.service;

import com.arenahub.backend.domain.Evento;
import com.arenahub.backend.dto.EventoRequestDTO;
import com.arenahub.backend.repository.EventoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EventoServiceTest {

    @Mock
    private EventoRepository eventoRepository;

    @InjectMocks
    private EventoService eventoService;

    private EventoRequestDTO criarDto() {
        return new EventoRequestDTO(
                "Show de Rock",
                "Grande show de rock",
                LocalDateTime.of(2025, 6, 1, 20, 0),
                LocalDateTime.of(2025, 6, 1, 23, 0),
                500,
                "Musica",
                1L
        );
    }

    private Evento criarEvento() {
        Evento evento = new Evento(
                "Show de Rock",
                "Grande show de rock",
                LocalDateTime.of(2025, 6, 1, 20, 0),
                LocalDateTime.of(2025, 6, 1, 23, 0),
                500,
                "Musica",
                1L
        );
        evento.setId(1L);
        return evento;
    }

    @Test
    void deveRetornarEventosFiltradosPorCategoriaEData() {
        String categoria = "Musica";
        LocalDateTime data = LocalDateTime.of(2025, 6, 1, 20, 0);
        List<Evento> esperado = List.of(criarEvento());
        when(eventoRepository.findByCategoriaAndData(categoria, data)).thenReturn(esperado);

        List<Evento> resultado = eventoService.listarEventosFiltrados(categoria, data);

        assertEquals(esperado, resultado);
        verify(eventoRepository).findByCategoriaAndData(categoria, data);
        verify(eventoRepository, never()).findAll();
    }

    @Test
    void deveRetornarTodosEventosQuandoAmbosFiltrosNulos() {
        List<Evento> esperado = List.of(criarEvento());
        when(eventoRepository.findAll()).thenReturn(esperado);

        List<Evento> resultado = eventoService.listarEventosFiltrados(null, null);

        assertEquals(esperado, resultado);
        verify(eventoRepository).findAll();
        verify(eventoRepository, never()).findByCategoriaAndData(any(), any());
    }

    @Test
    void deveRetornarTodosEventosQuandoApenasCategoriaInformada() {
        List<Evento> esperado = List.of(criarEvento());
        when(eventoRepository.findAll()).thenReturn(esperado);

        List<Evento> resultado = eventoService.listarEventosFiltrados("Musica", null);

        assertEquals(esperado, resultado);
        verify(eventoRepository).findAll();
        verify(eventoRepository, never()).findByCategoriaAndData(any(), any());
    }

    @Test
    void deveRetornarTodosEventosQuandoApenasDataInformada() {
        List<Evento> esperado = List.of(criarEvento());
        when(eventoRepository.findAll()).thenReturn(esperado);

        List<Evento> resultado = eventoService.listarEventosFiltrados(null, LocalDateTime.of(2025, 6, 1, 20, 0));

        assertEquals(esperado, resultado);
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
        when(eventoRepository.save(any(Evento.class))).thenReturn(eventoSalvo);

        Evento resultado = eventoService.cadastrarEvento(dto);

        assertNotNull(resultado);
        assertEquals(eventoSalvo, resultado);
        verify(eventoRepository).save(any(Evento.class));
    }

    @Test
    void deveCriarEventoComDadosCorretosDoDTOAoCadastrar() {
        EventoRequestDTO dto = criarDto();
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
        assertEquals("Musica", capturado.getCategoria());
        assertEquals(1L, capturado.getEspacoId());
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
                "Jazz",
                2L
        );
        when(eventoRepository.findById(1L)).thenReturn(Optional.of(existente));
        when(eventoRepository.save(existente)).thenReturn(existente);

        Evento resultado = eventoService.atualizarEvento(1L, dto);

        assertNotNull(resultado);
        assertEquals("Festival de Jazz", existente.getNome());
        assertEquals("Evento de jazz ao vivo", existente.getDescricao());
        assertEquals(LocalDateTime.of(2025, 7, 10, 18, 0), existente.getDataInicio());
        assertEquals(LocalDateTime.of(2025, 7, 10, 22, 0), existente.getDataFim());
        assertEquals(300, existente.getExpectativaPublico());
        assertEquals("Jazz", existente.getCategoria());
        assertEquals(2L, existente.getEspacoId());
        verify(eventoRepository).findById(1L);
        verify(eventoRepository).save(existente);
    }

    @Test
    void deveLancarExcecaoAoAtualizarEventoInexistente() {
        EventoRequestDTO dto = criarDto();
        when(eventoRepository.findById(99L)).thenReturn(Optional.empty());

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class,
                () -> eventoService.atualizarEvento(99L, dto));

        assertTrue(exception.getMessage().contains("99"));
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
