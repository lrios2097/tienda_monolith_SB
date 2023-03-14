package com.monoapp.tienda.Exception;

public class ResourceNotFoundException extends RuntimeException { // agrego la herencia de RUnTimeExeption
    public ResourceNotFoundException(String message) {
        super(message); //se utiliza el constructor de RuntimeException, agregando mi "message" al constructor
    }
}
