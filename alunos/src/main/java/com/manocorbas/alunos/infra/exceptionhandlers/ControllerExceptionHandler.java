package com.manocorbas.alunos.infra.exceptionhandlers;

import com.manocorbas.alunos.model.dtos.ExceptionDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ExceptionDTO> threatDuplicateEntry(DataIntegrityViolationException e){
        ExceptionDTO exceptionDTO = new ExceptionDTO("Aluno ja cadastrado", "400");
        return ResponseEntity.badRequest().body(exceptionDTO);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ExceptionDTO> threat404(EntityNotFoundException e){
        ExceptionDTO exceptionDTO = new ExceptionDTO(e.getMessage(), "404");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionDTO);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionDTO> threatGeneralExceptions(Exception e){
        ExceptionDTO exceptionDTO = new ExceptionDTO(e.getMessage(), "500");
        return ResponseEntity.internalServerError().body(exceptionDTO);
    }

}
