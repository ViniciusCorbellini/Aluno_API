package com.manocorbas.alunos.services;

import com.manocorbas.alunos.model.domain.Aluno;
import com.manocorbas.alunos.model.domain.Nota;
import com.manocorbas.alunos.model.dtos.AlunoDTO;
import com.manocorbas.alunos.repositories.AlunoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlunoService {
    private AlunoRepository alunoRepository;

    public AlunoService(AlunoRepository alunoRepository) {
        this.alunoRepository = alunoRepository;
    }

    public Aluno createAluno(AlunoDTO alunoDTO){
        Aluno aluno = new Aluno(alunoDTO);
        alunoRepository.save(aluno);
        return aluno;
    }

    public List<Aluno> getAllAlunos(){return alunoRepository.findAll();}

    public Aluno findAlunoById(Long id) throws EntityNotFoundException{
        return alunoRepository.findAlunoById(id).orElseThrow(() -> new EntityNotFoundException("Aluno nao encontrado!"));
    }

    public Aluno updateAluno(Long id, AlunoDTO novo) throws EntityNotFoundException{
        Aluno aluno = findAlunoById(id);

        aluno.setNome(novo.nome());
        aluno.setMatricula(novo.matricula());

        aluno.getNotas().clear();

        List<Nota> notas = novo.notas()
                .stream()
                .map(notaDTO -> new Nota(notaDTO, aluno))
                .toList();

        aluno.getNotas().addAll(notas);

        return alunoRepository.save(aluno);
    }

    public void deleteAlunoById(Long id) throws EntityNotFoundException{
        findAlunoById(id);
        alunoRepository.deleteById(id);
    }

    public Float calculateMedia(Aluno aluno) throws Exception{
        if(aluno.getNotas().isEmpty()) throw new Exception("O aluno nao possui notas cadastradas");

        float sum = 0;
        for (Nota n : aluno.getNotas()){
            sum += n.getValor_nota();
        }
        return (float) (sum/aluno.getNotas().size());
    }
}
