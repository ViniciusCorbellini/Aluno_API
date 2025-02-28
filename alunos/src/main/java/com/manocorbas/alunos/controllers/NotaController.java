package com.manocorbas.alunos.controllers;

import com.manocorbas.alunos.model.domain.Nota;
import com.manocorbas.alunos.model.dtos.NotaDTO;
import com.manocorbas.alunos.services.NotaService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notas")
public class NotaController {

    @Autowired
    private NotaService notaService;

    @PostMapping
    public ResponseEntity<Nota> createNota(@RequestBody NotaDTO notaDTO) throws EntityNotFoundException {
        Nota nota = notaService.createNota(notaDTO);
        return new ResponseEntity<>(nota, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Nota>> listAllNotas(){
        List<Nota> notas = notaService.listAllNotas();
        return new ResponseEntity<>(notas, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Nota> getNotaById(@PathVariable(value = "id") Long id) throws EntityNotFoundException {
        Nota nota = notaService.findNotaById(id);
        return new ResponseEntity<>(nota, HttpStatus.FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Nota> updateNota(@PathVariable(value = "id") Long id, @RequestBody NotaDTO notaDTO) throws EntityNotFoundException {
        Nota nota = notaService.updateNota(id, notaDTO);
        return new ResponseEntity<>(nota, HttpStatus.FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteNotaById(@PathVariable(value = "id") Long id) throws EntityNotFoundException{
        notaService.deleteNotaById(id);
        return ResponseEntity.status(HttpStatus.FOUND).body("Nota deletada");
    }
}
