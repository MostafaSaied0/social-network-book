package com.BSN.book_network.exception;

public class OperationNotPermittedException extends RuntimeException{
    public OperationNotPermittedException(){

    }

    public OperationNotPermittedException(String message){
        super(message);
    }
}
