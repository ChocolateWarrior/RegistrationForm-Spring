package de.springboot.dto;

import de.springboot.model.Specification;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationDTO {

    private String firstName;
    private String lastName;
    private String login;
    private String password;
    private Set<Specification> specifications;

    public RegistrationDTO(String firstName, String lastName, String login, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.login = login;
        this.password = password;
    }
}
