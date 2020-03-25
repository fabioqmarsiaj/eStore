package com.fabioqmarsiaj.estore.services.exceptions;

public class OrderNotFoundException extends RuntimeException{

    public OrderNotFoundException(String msg){
        super(msg);
    }

    public OrderNotFoundException(String msg, Throwable cause){
        super(msg, cause);
    }
}
