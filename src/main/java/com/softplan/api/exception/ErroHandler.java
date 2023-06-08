package com.softplan.api.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ErroHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity ErroHandler404()  {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity ErroHandler400(HttpMessageNotReadableException ex) {
        return ResponseEntity.badRequest().body("Erro!!! Campo fornecido e inválido.");
    }

    @ExceptionHandler(ApiException.class)
    public ResponseEntity ErroHandler400(ApiException ex){
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity ErroHandlerValidate(MethodArgumentNotValidException ex){
        List<String> errors = ex.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> fieldError.getField() + " " + fieldError.getDefaultMessage())
                .collect(Collectors.toList());
        ErrorResponse errorResponse = new ErrorResponse("Erro de validação", errors);
        return ResponseEntity.badRequest().body(errorResponse);
    }


}
