package de.springboot.web;

import de.springboot.model.User;
import de.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/display")
public class UserDisplay {

    private UserService userService;

    @Autowired
    public UserDisplay(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping
    public List<User> getListOfUsers(){
        return userService.getAllUsers();
    }
}
