package de.springboot.controller;

import de.springboot.dto.CommentDTO;
import de.springboot.dto.PaymentDTO;
import de.springboot.dto.RequestMasterDTO;
import de.springboot.model.RepairRequest;
import de.springboot.model.RequestState;
import de.springboot.service.RequestService;
import de.springboot.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Log4j2
@Controller

public class MainPageController {

    private final UserService userService;
    private final RequestService requestService;

    @Autowired
    public MainPageController(UserService userService,
                              RequestService requestService) {
        this.userService = userService;
        this.requestService = requestService;
    }

    @GetMapping("/main")
    public String showMain(Model model){

        List<RepairRequest> master_requests = requestService.getRequestsByMaster();
        List<RepairRequest> requests = requestService.getRequestsByUser();

        model.addAttribute("balance", userService.getUserBalance());
        model.addAttribute("master_requests", master_requests);
        model.addAttribute("user_requests", requests);
        model.addAttribute("paid", RequestState.PAID);
        model.addAttribute("completed", RequestState.COMPLETED);
        model.addAttribute("accepted", RequestState.ACCEPTED);

        return "index";
    }

    @PostMapping("main/payment")
    public String payForRequest(PaymentDTO dto, Model model){
        userService.setPurchase(dto.getRequestPrice(), dto.getRequestId());
        return "redirect:/main";
    }

    @PostMapping("main/comment")
    public String leaveComment(CommentDTO dto){
        requestService.setRequestComment(dto.getComment(), dto.getRequestId());
        return "redirect:/main";
    }

    @PostMapping("/main/complete")
    public String completeRequest(RequestMasterDTO dto){
        requestService.setRequestComplete(dto.getRequestId());
        return "index";
    }
}
