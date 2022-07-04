package br.com.mentorama.cadastro;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GerenicControllerAdvice {
    @ExceptionHandler({RecursoInexistenteException.class})
    public ResponseEntity<String> handle(final RecursoInexistenteException e){
        return new ResponseEntity<>("Recurso inexistente", HttpStatus.NOT_FOUND);
    }
}
