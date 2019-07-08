package de.springboot.service;

import de.springboot.dto.LoginDTO;
import de.springboot.exceptions.CredentialsException;
import de.springboot.model.User;
import de.springboot.repository.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class LoginService {

    private final UserRepository userRepository;

    @Autowired
    public LoginService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public User getUser(LoginDTO dto) throws CredentialsException{
        User user = userRepository.findByLoginAndPassword(dto.getLogin(), dto.getPassword());

        if(user == null) {
            log.warn(dto + " there is no such user record in database");
            throw new CredentialsException("Invalid credentials");
        }

        if (!dto.getPassword().equals(user.getPassword())) {
            log.warn(dto + " incorrect password");
            throw new CredentialsException("Invalid credentials");
        }

        log.info(dto + " user successfully logged in");
        return user;
    }

}
