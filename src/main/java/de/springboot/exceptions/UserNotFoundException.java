package de.springboot.exceptions;

import org.springframework.beans.factory.annotation.Autowired;

public class UserNotFoundException extends Exception{

    private final String reason;
    private final String wrongArgument;
    public UserNotFoundException(String reason, String wrongArgument){
        this.reason = reason;
        this.wrongArgument = wrongArgument;
    }
}
