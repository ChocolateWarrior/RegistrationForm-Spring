package de.springboot.exceptions;

public class UserNotFoundException extends Exception{

    private String reason;
    private String wrongArgument;
    public UserNotFoundException(String reason, String wrongArgument){
        this.reason = reason;
        this.wrongArgument = wrongArgument;
    }
}
