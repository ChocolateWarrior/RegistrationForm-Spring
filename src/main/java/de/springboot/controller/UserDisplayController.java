package de.springboot.controller;

import de.springboot.dto.RegistrationDTO;
import de.springboot.model.User;
import de.springboot.service.UserDisplayService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
//        log.info(requests.toString());
        model.addAttribute("all_users", users);
        model.addAttribute("user", new User());
        return "display";
    }

    @PostMapping("/user-display/remove/{id}")
    public String removeUser(@PathVariable("id") int userId,
                                 Model model) {
        User user = userDisplayService.getUserById(userId);
        userDisplayService.removeUser(user);
        model.addAttribute("all_users", userDisplayService.getAllUsers());
        return "display";
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/user-display/edit/{id}")
    public String editUser(RegistrationDTO dto, @PathVariable("id") int userId,
                                Model model) {
        User user = userDisplayService.getUserById(userId);
//        RegistrationDTO dto = new RegistrationDTO();
        model.addAttribute("all_users", userDisplayService.getAllUsers());
        model.addAttribute("user_dto", dto);
//        userDisplayService.editUser(user);
        return "display";
    }

}
