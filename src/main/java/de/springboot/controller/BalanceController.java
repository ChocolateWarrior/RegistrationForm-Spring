package de.springboot.controller;

import de.springboot.dto.BalanceDTO;
import de.springboot.service.MainPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BalanceController {

    private final MainPageService mainPageService;

    @Autowired
    public BalanceController(MainPageService mainPageService) {
        this.mainPageService = mainPageService;
    }

    @GetMapping("/balance")
    public String showBalancePage(Model model){
        model.addAttribute("balance", mainPageService.getUserBalance());
        return "balance";
    }

    @PostMapping("/balance")
    public String replenishBalance(BalanceDTO dto){
        mainPageService.addToUserBalance(dto.getBalance());
        return "redirect:/main";
    }

}
