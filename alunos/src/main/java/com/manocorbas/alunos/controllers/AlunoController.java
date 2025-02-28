package com.manocorbas.alunos.controllers;


import com.manocorbas.alunos.model.domain.Aluno;
import com.manocorbas.alunos.model.dtos.AlunoDTO;
import com.manocorbas.alunos.model.dtos.AprovacaoDTO;
import com.manocorbas.alunos.services.AlunoService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    @Autowired
    private AlunoService alunoService;

    @PostMapping
    public ResponseEntity<Aluno> createAluno(@RequestBody AlunoDTO alunoDTO){
        Aluno novo_aluno = alunoService.createAluno(alunoDTO);
        return new ResponseEntity<>(novo_aluno, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Aluno>> getAlunos(){
        List<Aluno> alunos = alunoService.getAllAlunos();
        return new ResponseEntity<>(alunos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Aluno> getAlunoById(@PathVariable(value = "id") Long id) throws EntityNotFoundException {
        Aluno aluno = alunoService.findAlunoById(id);
        return new ResponseEntity<>(aluno, HttpStatus.FOUND);
    }

    @GetMapping("/media/{id}")
    public ResponseEntity<AprovacaoDTO> getAlunoAprovado(@PathVariable(value = "id") Long id) throws Exception, EntityNotFoundException {
        Aluno aluno = alunoService.findAlunoById(id);
        Float media = alunoService.calculateMedia(aluno);
        Boolean aproved = media >= 7;
        AprovacaoDTO aprovacaoDTO = new AprovacaoDTO(aluno, media, aproved);
        return new ResponseEntity<>(aprovacaoDTO, HttpStatus.FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Aluno> updateAlunos(@PathVariable(value = "id") Long id, @RequestBody AlunoDTO alunoDTO) throws EntityNotFoundException {
        Aluno aluno = alunoService.updateAluno(id, alunoDTO);
        return new ResponseEntity<>(aluno, HttpStatus.FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAluno(@PathVariable(value = "id") Long id) throws EntityNotFoundException{
        alunoService.deleteAlunoById(id);
        return ResponseEntity.status(HttpStatus.FOUND).body("Aluno deletado");
    }
}
