package com.arenahub.backend.service;

import com.arenahub.backend.domain.Evento;
import com.arenahub.backend.domain.categoria.CategoriaEvento;
import com.arenahub.backend.domain.espaco.Espaco;
import com.arenahub.backend.domain.solicitacao.SolicitacaoEvento;
import com.arenahub.backend.domain.solicitacao.StatusSolicitacaoEvento;
import com.arenahub.backend.dto.AprovarSolicitacaoEventoResponseDTO;
import com.arenahub.backend.dto.RejeitarSolicitacaoEventoRequestDTO;
import com.arenahub.backend.dto.SolicitacaoEventoRequestDTO;
import com.arenahub.backend.dto.SolicitacaoEventoResponseDTO;
import com.arenahub.backend.repository.CategoriaEventoRepository;
import com.arenahub.backend.repository.EspacoRepository;
import com.arenahub.backend.repository.EventoRepository;
import com.arenahub.backend.repository.SolicitacaoEventoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SolicitacaoEventoService {

    private final SolicitacaoEventoRepository solicitacaoEventoRepository;
    private final CategoriaEventoRepository categoriaEventoRepository;
    private final EspacoRepository espacoRepository;
    private final EventoRepository eventoRepository;

    public SolicitacaoEventoService(
            SolicitacaoEventoRepository solicitacaoEventoRepository,
            CategoriaEventoRepository categoriaEventoRepository,
            EspacoRepository espacoRepository,
            EventoRepository eventoRepository) {
        this.solicitacaoEventoRepository = solicitacaoEventoRepository;
        this.categoriaEventoRepository = categoriaEventoRepository;
        this.espacoRepository = espacoRepository;
        this.eventoRepository = eventoRepository;
    }

    public SolicitacaoEventoResponseDTO criarSolicitacao(SolicitacaoEventoRequestDTO dto) {
        validarDatas(dto);
        validarConflitoDeData(dto);

        CategoriaEvento categoria = categoriaEventoRepository.findById(dto.categoriaId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Categoria nao encontrada com id: " + dto.categoriaId()));

        Espaco espaco = espacoRepository.findById(dto.espacoId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Espaco nao encontrado com id: " + dto.espacoId()));

        SolicitacaoEvento solicitacao = new SolicitacaoEvento();
        solicitacao.setNomeEvento(dto.nomeEvento());
        solicitacao.setDescricao(dto.descricao());
        solicitacao.setDataInicio(dto.dataInicio());
        solicitacao.setDataFim(dto.dataFim());
        solicitacao.setExpectativaPublico(dto.expectativaPublico());
        solicitacao.setClassificacaoIndicativa(dto.classificacaoIndicativa());
        solicitacao.setCategoria(categoria);
        solicitacao.setEspaco(espaco);
        solicitacao.setNomeSolicitante(dto.nomeSolicitante());
        solicitacao.setEmailSolicitante(dto.emailSolicitante());
        solicitacao.setStatus(StatusSolicitacaoEvento.PENDENTE);
        solicitacao.setCriadaEm(LocalDateTime.now());
        solicitacao.setMotivoRejeicao(null);
        solicitacao.setAnalisadaEm(null);

        return toResponse(solicitacaoEventoRepository.save(solicitacao));
    }

    public List<SolicitacaoEventoResponseDTO> listarSolicitacoes() {
        return solicitacaoEventoRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public SolicitacaoEventoResponseDTO buscarSolicitacaoPorId(Long id) {
        SolicitacaoEvento solicitacao = solicitacaoEventoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Solicitacao de evento nao encontrada com id: " + id));

        return toResponse(solicitacao);
    }

    @Transactional
    public AprovarSolicitacaoEventoResponseDTO aprovarSolicitacao(Long id) {
        SolicitacaoEvento solicitacao = buscarSolicitacaoEntidadePorId(id);
        validarSolicitacaoPendente(solicitacao);

        Evento evento = new Evento(
                solicitacao.getNomeEvento(),
                solicitacao.getDescricao(),
                solicitacao.getDataInicio(),
                solicitacao.getDataFim(),
                solicitacao.getExpectativaPublico(),
                solicitacao.getClassificacaoIndicativa(),
                solicitacao.getCategoria(),
                solicitacao.getEspaco());

        Evento eventoSalvo = eventoRepository.save(evento);

        solicitacao.setStatus(StatusSolicitacaoEvento.APROVADA);
        solicitacao.setAnalisadaEm(LocalDateTime.now());
        solicitacao.setMotivoRejeicao(null);

        SolicitacaoEvento solicitacaoAtualizada = solicitacaoEventoRepository.save(solicitacao);
        return new AprovarSolicitacaoEventoResponseDTO(toResponse(solicitacaoAtualizada), eventoSalvo.getId());
    }

    @Transactional
    public SolicitacaoEventoResponseDTO rejeitarSolicitacao(
            Long id,
            RejeitarSolicitacaoEventoRequestDTO dto) {
        SolicitacaoEvento solicitacao = buscarSolicitacaoEntidadePorId(id);
        validarSolicitacaoPendente(solicitacao);

        solicitacao.setStatus(StatusSolicitacaoEvento.REJEITADA);
        solicitacao.setMotivoRejeicao(dto.motivo());
        solicitacao.setAnalisadaEm(LocalDateTime.now());

        return toResponse(solicitacaoEventoRepository.save(solicitacao));
    }

    private SolicitacaoEvento buscarSolicitacaoEntidadePorId(Long id) {
        return solicitacaoEventoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Solicitacao de evento nao encontrada com id: " + id));
    }

    private void validarSolicitacaoPendente(SolicitacaoEvento solicitacao) {
        if (solicitacao.getStatus() != StatusSolicitacaoEvento.PENDENTE) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Apenas solicitacoes pendentes podem ser aprovadas ou rejeitadas");
        }
    }

    private void validarDatas(SolicitacaoEventoRequestDTO dto) {
        if (!dto.dataFim().isAfter(dto.dataInicio())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "dataFim deve ser posterior a dataInicio");
        }
    }

    private void validarConflitoDeData(SolicitacaoEventoRequestDTO dto) {
        List<Evento> eventosConflitantes = eventoRepository.findConflitoDeDatas(
                dto.dataInicio(), dto.dataFim());

        if (!eventosConflitantes.isEmpty()) {
            String nomeEvento = eventosConflitantes.get(0).getNome();
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Ja existe um evento aprovado para essa data: " + nomeEvento
                            + ". Escolha outra data para o seu evento.");
        }
    }

    private SolicitacaoEventoResponseDTO toResponse(SolicitacaoEvento solicitacao) {
        SolicitacaoEventoResponseDTO dto = new SolicitacaoEventoResponseDTO();
        dto.setId(solicitacao.getId());
        dto.setNomeEvento(solicitacao.getNomeEvento());
        dto.setDescricao(solicitacao.getDescricao());
        dto.setDataInicio(solicitacao.getDataInicio());
        dto.setDataFim(solicitacao.getDataFim());
        dto.setExpectativaPublico(solicitacao.getExpectativaPublico());
        dto.setClassificacaoIndicativa(solicitacao.getClassificacaoIndicativa());
        dto.setStatus(solicitacao.getStatus());
        dto.setMotivoRejeicao(solicitacao.getMotivoRejeicao());
        dto.setCriadaEm(solicitacao.getCriadaEm());
        dto.setAnalisadaEm(solicitacao.getAnalisadaEm());
        dto.setCategoriaId(solicitacao.getCategoria().getId());
        dto.setCategoriaNome(solicitacao.getCategoria().getNome());
        dto.setEspacoId(solicitacao.getEspaco().getId());
        dto.setEspacoNome(solicitacao.getEspaco().getNome());
        dto.setNomeSolicitante(solicitacao.getNomeSolicitante());
        dto.setEmailSolicitante(solicitacao.getEmailSolicitante());
        return dto;
    }
}
