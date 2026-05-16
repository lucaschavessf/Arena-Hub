package com.arenahub.backend.infra;

import com.arenahub.backend.domain.CategoriaIngresso;
import com.arenahub.backend.repository.CategoriaIngressoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class DataSeeder {

    @Bean
    public CommandLineRunner carregarCategorias(CategoriaIngressoRepository repository) {
        return args -> {
            if (repository.count() == 0) {
                CategoriaIngresso pista = new CategoriaIngresso();
                pista.setNome("Pista");

                CategoriaIngresso pistaPremium = new CategoriaIngresso();
                pistaPremium.setNome("Pista Premium");

                CategoriaIngresso camarote = new CategoriaIngresso();
                camarote.setNome("Camarote");

                CategoriaIngresso arquibancada = new CategoriaIngresso();
                arquibancada.setNome("Arquibancada");

                CategoriaIngresso vip = new CategoriaIngresso();
                vip.setNome("Área VIP");
                
                CategoriaIngresso meia = new CategoriaIngresso();
                meia.setNome("Meia-Entrada");

                repository.saveAll(List.of(pista, pistaPremium, camarote, arquibancada, vip, meia));
                
                System.out.println("Categorias de ingresso populadas com sucesso!");
            }
        };
    }
}
