package com.fabioqmarsiaj.estore.services.exceptions;

public class CategoryNotFoundException extends RuntimeException{

    public CategoryNotFoundException(String msg){
        super(msg);
    }

    public CategoryNotFoundException(String msg, Throwable cause){
        super(msg, cause);
    }
}
