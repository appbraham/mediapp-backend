package com.appbraham.mediapp_backend.exception;

public class ModeloNotFoundException extends RuntimeException {

    public ModeloNotFoundException(String mensaje){
        super(mensaje);
    }

}
