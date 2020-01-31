package helvetica.application.controllers;

import helvetica.application.dtos.RegistrationDTO;
import helvetica.application.services.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Log4j2
@ Controller
@RequestMapping("/user-display")
public class UserDisplayController {

    private final UserService userService;

    @Autowired
    UserDisplayController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String showUsers(Model model) {
        model.addAttribute("all_users", userService.getAllUsers());
        return "display";
    }

    @PostMapping("/remove/{id}")
    public String removeUser(@PathVariable("id") int userId) {
        userService.removeUser(userId);
        return "redirect:/user-display";
    }

    @PostMapping("/edit/{id}")
    public String editUser(@PathVariable("id") int userId,
                           RegistrationDTO dto) {

        if (!dto.getFirstName().isEmpty())
            userService.setUserFirstName(userId, dto.getFirstName());
        if (!dto.getLastName().isEmpty())
            userService.setUserLastName(userId, dto.getLastName());
        if (!dto.getLogin().isEmpty())
            userService.setUserLogin(userId, dto.getLogin());
        if (!dto.getPassword().isEmpty())
            userService.setUserPassword(userId,
                    new BCryptPasswordEncoder().encode(dto.getPassword()));
        return "redirect:/user-display";
    }

    @GetMapping("/edit/{id}")
    public String getEditPage(@PathVariable("id") int userId, Model model) {
        model.addAttribute("userId", userId);
        return "user_edit";
    }


}
