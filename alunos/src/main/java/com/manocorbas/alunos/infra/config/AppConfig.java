package com.manocorbas.alunos.infra.config;

import com.manocorbas.alunos.repositories.AlunoRepository;
import com.manocorbas.alunos.repositories.NotaRepository;
import com.manocorbas.alunos.services.AlunoService;
import com.manocorbas.alunos.services.NotaService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public NotaService notaService(NotaRepository notaRepository, AlunoService alunoService){
        return new NotaService(notaRepository, alunoService);
    }

    @Bean
    public AlunoService alunoService(AlunoRepository alunoRepository){
        return new AlunoService(alunoRepository);
    }

}
