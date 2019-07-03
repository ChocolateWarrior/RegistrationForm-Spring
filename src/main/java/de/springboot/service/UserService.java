package de.springboot.service;

import de.springboot.dto.DTO;
import de.springboot.exceptions.UserNotFoundException;
import de.springboot.model.User;
import de.springboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.NestedExceptionUtils;
import org.springframework.stereotype.Service;
import lombok.extern.log4j.Log4j2;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers(){
        List <User> res = new ArrayList<>();
        userRepository.findAll().forEach(res::add);
        return res;
    }

    public void pushUser(User user){
        try {
            userRepository.save(user);
        } catch (Exception e) {

        }
    }

    public User getUser(DTO dto){
        User user = userRepository.findByLogin(dto.getLogin());
        if (user == null) {
//            throw new UserNotFoundException("No user with such login", dto.getLogin());
        }
        if (dto.getPassword().hashCode() != user.getPassword().hashCode()) {
//            throw new WrongPasswordException("Wrong password for this user", dto.getLogin());
        }
//        log.info("Returning user: " + user.toString());
        return user;
    }

}
