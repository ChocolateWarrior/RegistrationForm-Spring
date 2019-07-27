package de.springboot.service;

import de.springboot.model.RepairRequest;
import de.springboot.model.User;
import de.springboot.repository.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public void setUserFirstName(int userId, String firstName){
        User user = getUserById(userId);
        user.setFirstName(firstName);
        userRepository.save(user);
    }


    public void setUserLastName(int userId, String lastName){
        User user = getUserById(userId);
        user.setLastName(lastName);
        userRepository.save(user);
    }


    public void setUserLogin(int userId, String login){
        User user = getUserById(userId);
        user.setUsername(login);
        userRepository.save(user);
    }

    public void setUserPassword(int userId, String password){
        User user = getUserById(userId);
        user.setPassword(password);
        userRepository.save(user);
    }

    public void removeUser(int userId){
        User user = getUserById(userId);
        userRepository.delete(user);
    }

    public void addMasterRequest(User master, RepairRequest request){
        master.addMasterRequest(request);
        userRepository.save(master);
    }

}
