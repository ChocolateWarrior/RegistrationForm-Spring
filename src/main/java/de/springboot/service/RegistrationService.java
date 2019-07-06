package de.springboot.service;

import de.springboot.dto.RegistrationDTO;
import de.springboot.model.Role;
import de.springboot.model.User;
import de.springboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class RegistrationService {

    private final UserRepository userRepository;

    @Autowired
    public RegistrationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void pushUser(RegistrationDTO dto){
        User userToAdd = User.builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .active(true)
                .login(dto.getLogin())
                .password(dto.getPassword())
                .roles(Collections.singleton(Role.USER))
                .build();


        userRepository.save(userToAdd);
    }
}
