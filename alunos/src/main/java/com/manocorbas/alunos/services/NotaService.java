package com.manocorbas.alunos.services;

import com.manocorbas.alunos.model.domain.Nota;
import com.manocorbas.alunos.model.dtos.NotaDTO;
import com.manocorbas.alunos.repositories.NotaRepository;

import java.util.List;

public class NotaService {
    private NotaRepository notaRepository;
    private AlunoService alunoService;

    public NotaService(NotaRepository notaRepository) {
        this.notaRepository = notaRepository;
    }

    public Nota createNota(NotaDTO dto){
        Nota nota = new Nota(dto);
        return notaRepository.save(nota);
    }

    public List<Nota> listAllNotas(){ return notaRepository.findAll();}

    public Nota findNotaById(Long id) throws Exception {
        return notaRepository.findById(id).orElseThrow(() -> new Exception("Nota not found"));
    }

    public List<Nota> findNotasByAluno(Long id_aluno) throws Exception {
        return alunoService.findAlunoById(id_aluno).getNotas();
    }

    public Nota updateNota(Long id, NotaDTO nova) throws Exception{
        Nota antiga = findNotaById(id);

        antiga.setValor_nota(nova.valor());
        antiga.setAluno(nova.aluno());
        antiga.setNome(nova.nome());

        return notaRepository.save(antiga);
    }

    public void deleteNotaById(Long id){
        notaRepository.deleteById(id);
    }
}
