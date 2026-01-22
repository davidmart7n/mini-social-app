package com.minisocial.exception;

public class CreatorNotFoundException extends RuntimeException{

    public CreatorNotFoundException(Long creatorId){

        super("The creator with id "+ creatorId + " was not found.");
    }
    
}
