package com.arenahub.backend.repository;

import com.arenahub.backend.domain.Evento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface EventoRepository extends JpaRepository<Evento, Long> {

    /**
     * Busca eventos por categoria e uma data/hora de início específica.
     * 
     * @param categoria A categoria do evento.
     * @param data      A data de início do evento.
     * @return Uma lista de eventos que correspondem aos critérios.
     */
    @Query("SELECT e FROM Evento e WHERE e.categoria.nome = :categoria AND e.dataInicio >= :data")
    List<Evento> findByCategoriaAndData(@Param("categoria") String categoria, @Param("data") LocalDateTime data);

    /**
     * Busca eventos que possuem sobreposição de datas com o intervalo informado.
     * Um evento conflita se seu início é antes do fim do intervalo E seu fim é depois do início do intervalo.
     */
    @Query("SELECT e FROM Evento e WHERE e.dataInicio < :dataFim AND e.dataFim > :dataInicio")
    List<Evento> findConflitoDeDatas(@Param("dataInicio") LocalDateTime dataInicio, @Param("dataFim") LocalDateTime dataFim);
}
