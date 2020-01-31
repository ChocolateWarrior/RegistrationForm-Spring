package helvetica.application.controllers;

import helvetica.application.dtos.BalanceDTO;
import helvetica.application.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/balance")
public class BalanceController {

    private final UserService userService;

    @Autowired
    public BalanceController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String showBalancePage(Model model){
        model.addAttribute("balance", userService.getUserBalance());
        return "balance";
    }

    @PostMapping
    public String replenishBalance(BalanceDTO dto){
        userService.addToUserBalance(dto.getBalance());
        return "redirect:/main";
    }

}
