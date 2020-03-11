package com.fabioqmarsiaj.estore.services.exceptions;

public class ClientNotFoundException extends RuntimeException{

    public ClientNotFoundException(String msg){
        super(msg);
    }

    public ClientNotFoundException(String msg, Throwable cause){
        super(msg, cause);
    }
}
