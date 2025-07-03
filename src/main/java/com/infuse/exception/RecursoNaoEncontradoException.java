package com.infuse.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class RecursoNaoEncontradoException extends RuntimeException {
    
    public RecursoNaoEncontradoException(String message) {
        super(message);
    }
    
    public RecursoNaoEncontradoException(String recurso, String campo, String valor) {
        super(String.format("%s não encontrado com %s: '%s'", recurso, campo, valor));
    }
} 