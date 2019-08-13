package de.springboot.controller;

import de.springboot.dto.RegistrationDTO;
import de.springboot.model.User;
import de.springboot.service.UserDisplayService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Log4j2
@Controller
public class UserDisplayController {

    private final UserDisplayService userDisplayService;

    @Autowired
    UserDisplayController(UserDisplayService userDisplayService){
        this.userDisplayService=userDisplayService;
    }

    @GetMapping("/user-display")
    public String showUsers(Model model){
        List<User> users = userDisplayService.getAllUsers();
        model.addAttribute("all_users", users);
        model.addAttribute("user", new User());
        return "display";
    }

    @PostMapping("/user-display/remove/{id}")
    public String removeUser(@PathVariable("id") int userId) {
        userDisplayService.removeUser(userId);
        return "redirect:/user-display";
    }

    @PostMapping("/user-display/edit/{id}")
    public String editUser(@PathVariable("id") int userId,
                           RegistrationDTO dto) {

        if(!dto.getFirstName().equals(""))
            userDisplayService.setUserFirstName(userId, dto.getFirstName());
        if(!dto.getLastName().equals(""))
            userDisplayService.setUserLastName(userId, dto.getLastName());
        if(!dto.getLogin().equals(""))
            userDisplayService.setUserLogin(userId, dto.getLogin());
        if(!dto.getPassword().equals(""))
            userDisplayService.setUserPassword(userId, new BCryptPasswordEncoder().encode(dto.getPassword()));
        return "redirect:/user-display";
    }

    @GetMapping("/user-display/edit/{id}")
    public String getEditPage(@PathVariable("id") int userId, Model model){
        model.addAttribute("userId", userId);
        return "user_edit";
    }


}
