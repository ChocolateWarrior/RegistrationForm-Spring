package de.springboot.controller;

import de.springboot.dto.BalanceDTO;
import de.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BalanceController {

    private final UserService userService;

    @Autowired
    public BalanceController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/balance")
    public String showBalancePage(Model model){
        model.addAttribute("balance", userService.getUserBalance());
        return "balance";
    }

    @PostMapping("/balance")
    public String replenishBalance(BalanceDTO dto){
        userService.addToUserBalance(dto.getBalance());
        return "redirect:/main";
    }

}
