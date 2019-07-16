package de.springboot.controller;

import de.springboot.model.User;
import de.springboot.service.UserDisplayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/display")
public class DisplayController {

    private UserDisplayService userDisplayService;

    @Autowired
    public DisplayController(UserDisplayService userDisplayService) {
            this.userDisplayService = userDisplayService;
        }

    @GetMapping
    public List<User> getListOfUsers(){
            return userDisplayService.getAllUsers();
        }



}
