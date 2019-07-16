package de.springboot.service;

import de.springboot.model.User;
import de.springboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.log4j.Log4j2;

import java.util.ArrayList;
import java.util.List;

@Log4j2
@Service
public class UserDisplayService {

    private final UserRepository userRepository;

    @Autowired
    public UserDisplayService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers(){
        List <User> res = new ArrayList<>();
        userRepository.findAll().forEach(res::add);
        return res;
    }

    public User getUserById(int id){
        return userRepository.findById(id);
    }

    public void removeUser(User user){
        userRepository.delete(user);
    }

}
