package com.manresa.serviciotecnico.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> manejarNoEncontrado(NoSuchElementException ex) {

        // respuesta linda
        String mensajeAmigable = "Error: El registro que estás buscando no existe en la base de datos.";

        // Devolvemos el mensaje con el código 404 (NOT_FOUND) en vez del feo 500
        return new ResponseEntity<>(mensajeAmigable, HttpStatus.NOT_FOUND);
    }
}