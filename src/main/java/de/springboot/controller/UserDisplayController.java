package de.springboot.controller;

import de.springboot.dto.RegistrationDTO;
import de.springboot.model.User;
import de.springboot.service.UserService;
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

    private final UserService userService;

    @Autowired
    UserDisplayController(UserService userService){
        this.userService=userService;
    }

    @GetMapping("/user-display")
    public String showUsers(Model model){
        List<User> users = userService.getAllUsers();
        model.addAttribute("all_users", users);
        model.addAttribute("user", new User());
        return "display";
    }

    @PostMapping("/user-display/remove/{id}")
    public String removeUser(@PathVariable("id") int userId) {
        userService.removeUser(userId);
        return "redirect:/user-display";
    }

    @PostMapping("/user-display/edit/{id}")
    public String editUser(@PathVariable("id") int userId,
                           RegistrationDTO dto) {

        if(!dto.getFirstName().equals(""))
            userService.setUserFirstName(userId, dto.getFirstName());
        if(!dto.getLastName().equals(""))
            userService.setUserLastName(userId, dto.getLastName());
        if(!dto.getLogin().equals(""))
            userService.setUserLogin(userId, dto.getLogin());
        if(!dto.getPassword().equals(""))
            userService.setUserPassword(userId, new BCryptPasswordEncoder().encode(dto.getPassword()));
        return "redirect:/user-display";
    }

    @GetMapping("/user-display/edit/{id}")
    public String getEditPage(@PathVariable("id") int userId, Model model){
        model.addAttribute("userId", userId);
        return "user_edit";
    }


}
