package com.minisocial.exception;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException(Long userId){

    super("The user with Id "+ userId + " was not found.");

    }
}