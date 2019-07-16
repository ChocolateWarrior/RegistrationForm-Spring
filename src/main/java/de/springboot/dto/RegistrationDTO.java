package de.springboot.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
//@NoArgsConstructor
public class RegistrationDTO {

    private String firstName;
    private String lastName;
    private String login;
    private String password;

    public RegistrationDTO() {
        this.firstName="Undefined";
        this.lastName="Undefined";
        this.login="Undefined";
        this.password="Undefined";

    }
}
