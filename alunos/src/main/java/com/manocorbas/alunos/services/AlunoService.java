package com.manocorbas.alunos.services;

import com.manocorbas.alunos.model.domain.Aluno;
import com.manocorbas.alunos.model.domain.Nota;
import com.manocorbas.alunos.model.dtos.AlunoDTO;
import com.manocorbas.alunos.repositories.AlunoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlunoService {
    public final AlunoRepository alunoRepository;

    public AlunoService(AlunoRepository alunoRepository) {
        this.alunoRepository = alunoRepository;
    }

    public Aluno createAluno(AlunoDTO alunoDTO){
        Aluno aluno = new Aluno(alunoDTO);
        alunoRepository.save(aluno);
        return aluno;
    }

    public List<Aluno> getAllAlunos(){return alunoRepository.findAll();}

    public Aluno findAlunoById(Long id) throws Exception{
        return alunoRepository.findAlunoById(id).orElseThrow(() -> new Exception("Aluno nao encontrado!"));
    }

    public Aluno updateAluno(Long id, AlunoDTO novo) throws Exception{
        Aluno aluno = findAlunoById(id);

        aluno.setNome(novo.nome());
        aluno.setMatricula(novo.matricula());
        aluno.setNotas(novo.notas());

        return alunoRepository.save(aluno);
    }

    public void deleteAluno(Long id){alunoRepository.deleteById(id);}

    public Float calculateMedia(Aluno aluno) throws Exception{
        if(aluno.getNotas().isEmpty()) throw new Exception("O aluno nao possui notas cadastradas");

        float sum = 0;
        for (Nota n : aluno.getNotas()){
            sum += n.getValor_nota();
        }
        return (float) (sum/aluno.getNotas().size());
    }

    public boolean aproved(Aluno aluno) throws Exception{
        return calculateMedia(aluno) >= 7;
    }
}
