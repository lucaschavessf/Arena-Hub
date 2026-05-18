package com.arenahub.backend.service;

import com.arenahub.backend.domain.Evento;
import com.arenahub.backend.domain.categoria.CategoriaEvento;
import com.arenahub.backend.domain.espaco.Espaco;
import com.arenahub.backend.dto.EventoRequestDTO;
import com.arenahub.backend.repository.CategoriaEventoRepository;
import com.arenahub.backend.repository.EspacoRepository;
import com.arenahub.backend.repository.EventoRepository;
import com.arenahub.backend.repository.UserRepository;
import com.arenahub.backend.domain.Produtor;
import com.arenahub.backend.domain.User;
import com.arenahub.backend.domain.evento.StatusEvento;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.Duration;
import java.util.List;
import java.util.Optional;

import java.util.stream.Collectors;
import com.arenahub.backend.dto.EventoDetalhesDTO;
import com.arenahub.backend.dto.LoteResponseDTO;
import com.arenahub.backend.dto.EventoResumoDTO;
import com.arenahub.backend.dto.EventoResponseDTO;

@Service
public class EventoService {

    private final EventoRepository eventoRepository;
    private final CategoriaEventoRepository categoriaRepository;
    private final EspacoRepository espacoRepository;
    private final UserRepository userRepository;

    public EventoService(EventoRepository eventoRepository, CategoriaEventoRepository categoriaRepository,
            EspacoRepository espacoRepository, UserRepository userRepository) {
        this.eventoRepository = eventoRepository;
        this.categoriaRepository = categoriaRepository;
        this.espacoRepository = espacoRepository;
        this.userRepository = userRepository;
    }

    public List<EventoResponseDTO> listarEventosFiltrados(String categoria, LocalDateTime data) {
        List<Evento> eventos;
        if (categoria != null && data != null) {
            eventos = eventoRepository.findByCategoriaAndData(categoria, data);
        } else {
            eventos = eventoRepository.findAll();
        }
        return eventos.stream().map(this::mapearParaDTO).collect(Collectors.toList());
    }

    public List<EventoResponseDTO> listarEventosAprovados() {
        List<Evento> eventos = eventoRepository.findByStatus(StatusEvento.APROVADO);
        return eventos.stream().map(this::mapearParaDTO).collect(Collectors.toList());
    }

    public List<EventoResponseDTO> listarEventosDoProdutor() {
        User usuarioLogado = getUsuarioLogado();
        if (!(usuarioLogado instanceof Produtor)) {
            throw new RuntimeException("Apenas produtores podem acessar seus eventos.");
        }
        List<Evento> eventos = eventoRepository.findByProdutorId(usuarioLogado.getId());
        return eventos.stream().map(this::mapearParaDTO).collect(Collectors.toList());
    }

    public Optional<Evento> listarEventoPorId(Long id) {
        return eventoRepository.findById(id);
    }

    public Optional<EventoDetalhesDTO> buscarDetalhesPorId(Long id) {
        return eventoRepository.findById(id).map(evento -> {
            List<LoteResponseDTO> lotesDTO = (evento.getLotes() == null) ? List.of() : evento.getLotes().stream()
                    .map(lote -> new LoteResponseDTO(
                            lote.getId(),
                            lote.getNome(),
                            lote.getPreco(),
                            lote.getQuantidadeTotal(),
                            lote.getQuantidadeTotal() - lote.getQuantidadeDisponivel(),
                            lote.getQuantidadeDisponivel(),
                            lote.getCategoria() != null ? lote.getCategoria().getNome() : null,
                            lote.getStatus() != null ? lote.getStatus().name() : null))
                    .collect(Collectors.toList());

            return new EventoDetalhesDTO(
                    evento.getId(),
                    evento.getNome(),
                    evento.getDescricao(),
                    evento.getDataInicio(),
                    evento.getDataFim(),
                    evento.getExpectativaPublico(),
                    evento.getClassificacaoIndicativa(),
                    evento.getCategoria() != null ? evento.getCategoria().getNome() : null,
                    evento.getEspaco() != null ? evento.getEspaco().getNome() : null,
                    lotesDTO
            );
        });
    }

    public List<EventoResumoDTO> listarMeusEventosAprovados() {
        User usuarioLogado = getUsuarioLogado();

        if (!(usuarioLogado instanceof Produtor)) {
            throw new RuntimeException("Apenas produtores podem acessar seus eventos.");
        }
        
        List<Evento> eventos = eventoRepository.findByProdutorIdAndStatus(usuarioLogado.getId(), StatusEvento.APROVADO);
        
        return eventos.stream().map(e -> new EventoResumoDTO(
            e.getId(), 
            e.getNome(), 
            e.getDataInicio(), 
            e.getEspaco() != null ? e.getEspaco().getNome() : "Não definido",
            0,
            0.0
        )).collect(Collectors.toList());
    }

    public boolean validarDisponibilidade(Long espacoId, LocalDateTime dataInicio, LocalDateTime dataFim) {
        return !eventoRepository.existsConflitoDeAgenda(
                espacoId,
                dataInicio,
                dataFim,
                List.of(StatusEvento.REJEITADO, StatusEvento.CANCELADO)
        );
    }

    public EventoResponseDTO cadastrarEvento(EventoRequestDTO data) {
        User usuarioLogado = getUsuarioLogado();

        if (!(usuarioLogado instanceof Produtor)) {
            throw new RuntimeException("Apenas produtores podem cadastrar eventos.");
        }

        Produtor produtor = (Produtor) usuarioLogado;

        CategoriaEvento categoria = null;
        if (data.categoriaId() != null) {
            categoria = categoriaRepository.findById(data.categoriaId())
                    .orElseThrow(() -> new EntityNotFoundException("Categoria de evento não encontrada"));
        }

        Espaco espaco = null;
        if (data.espacoId() != null) {
            espaco = espacoRepository.findById(data.espacoId())
                    .orElseThrow(() -> new EntityNotFoundException("Espaço não encontrado"));
        }

        if (data.dataInicio() != null && data.dataFim() != null) {
            long dias = Duration.between(data.dataInicio(), data.dataFim()).toDays();
            if (dias > 7) {
                throw new IllegalArgumentException("O evento não pode ter duração superior a 7 dias.");
            }
        }

        if (espaco != null) {
            if (data.expectativaPublico() != null && data.expectativaPublico() > espaco.getCapacidade()) {
                throw new IllegalArgumentException("A expectativa de público excede a capacidade do espaço selecionado.");
            }

            if (data.dataInicio() != null && data.dataFim() != null) {
                boolean conflito = eventoRepository.existsConflitoDeAgenda(
                        espaco.getId(),
                        data.dataInicio(),
                        data.dataFim(),
                        List.of(StatusEvento.REJEITADO, StatusEvento.CANCELADO)
                );
                if (conflito) {
                    throw new IllegalArgumentException("Conflito de agenda: O espaço já possui um evento marcado nesse período.");
                }
            }
        }

        Evento evento = new Evento(
                data.nome(),
                data.descricao(),
                data.dataInicio(),
                data.dataFim(),
                data.expectativaPublico(),
                data.classificacaoIndicativa(),
                categoria,
                espaco,
                produtor);

        eventoRepository.save(evento);
        return mapearParaDTO(evento);
    }

    public EventoResponseDTO atualizarEvento(Long id, EventoRequestDTO data) {
        Evento evento = eventoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Evento não encontrado."));

        if (data.nome() != null)
            evento.setNome(data.nome());
        if (data.descricao() != null)
            evento.setDescricao(data.descricao());
        if (data.dataInicio() != null)
            evento.setDataInicio(data.dataInicio());
        if (data.dataFim() != null)
            evento.setDataFim(data.dataFim());
        if (data.expectativaPublico() != null)
            evento.setExpectativaPublico(data.expectativaPublico());
        if (data.classificacaoIndicativa() != null)
            evento.setClassificacaoIndicativa(data.classificacaoIndicativa());

        if (data.categoriaId() != null) {
            CategoriaEvento categoria = categoriaRepository.findById(data.categoriaId())
                    .orElseThrow(() -> new RuntimeException("Categoria não encontrada."));
            evento.setCategoria(categoria);
        }

        if (data.espacoId() != null) {
            Espaco espaco = espacoRepository.findById(data.espacoId())
                    .orElseThrow(() -> new RuntimeException("Espaço não encontrado."));
            evento.setEspaco(espaco);
        }

        eventoRepository.save(evento);
        return mapearParaDTO(evento);
    }

    public void removerEvento(Long id) {
        if (!eventoRepository.existsById(id)) {
            throw new EntityNotFoundException("Evento não encontrado com id: " + id);
        }
        eventoRepository.deleteById(id);
    }

    public EventoResponseDTO analisarEvento(Long id, boolean aprovado, String motivoRejeicao) {
        Evento evento = eventoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Evento não encontrado"));

        if (evento.getStatus() != StatusEvento.PENDENTE) {
            throw new RuntimeException("Apenas eventos PENDENTE podem ser analisados.");
        }

        evento.setAnalisadaEm(LocalDateTime.now());

        if (aprovado) {
            evento.setStatus(StatusEvento.APROVADO);
            evento.setMotivoRejeicao(null);
        } else {
            if (motivoRejeicao == null || motivoRejeicao.trim().isEmpty()) {
                throw new RuntimeException("É obrigatório fornecer um motivo para rejeição.");
            }
            evento.setStatus(StatusEvento.REJEITADO);
            evento.setMotivoRejeicao(motivoRejeicao);
        }

        eventoRepository.save(evento);
        return mapearParaDTO(evento);
    }

    public EventoResponseDTO mapearParaDTO(Evento evento) {
        List<LoteResponseDTO> lotesDTO = (evento.getLotes() == null) ? List.of() : evento.getLotes().stream()
                .map(lote -> new LoteResponseDTO(
                        lote.getId(),
                        lote.getNome(),
                        lote.getPreco(),
                        lote.getQuantidadeTotal(),
                        lote.getQuantidadeTotal() - lote.getQuantidadeDisponivel(),
                        lote.getQuantidadeDisponivel(),
                        lote.getCategoria() != null ? lote.getCategoria().getNome() : null,
                        lote.getStatus() != null ? lote.getStatus().name() : null))
                .collect(Collectors.toList());

        return new EventoResponseDTO(
                evento.getId(),
                evento.getNome(),
                evento.getDescricao(),
                evento.getDataInicio(),
                evento.getDataFim(),
                evento.getExpectativaPublico(),
                evento.getCategoria() != null ? evento.getCategoria().getNome() : null,
                evento.getEspaco() != null ? evento.getEspaco().getNome() : null,
                evento.getClassificacaoIndicativa() != null ? evento.getClassificacaoIndicativa().name() : null,
                evento.getStatus() != null ? evento.getStatus().name() : null,
                lotesDTO
        );
    }

    private User getUsuarioLogado() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof User) {
            return (User) principal;
        }
        throw new EntityNotFoundException("Usuário logado não encontrado");
    }
}
