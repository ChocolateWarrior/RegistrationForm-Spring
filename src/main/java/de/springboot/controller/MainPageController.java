package de.springboot.controller;

import de.springboot.dto.CommentDTO;
import de.springboot.dto.PaymentDTO;
import de.springboot.dto.RequestMasterDTO;
import de.springboot.model.RepairRequest;
import de.springboot.model.RequestState;
import de.springboot.service.MainPageService;
import de.springboot.service.RequestDisplayService;
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

    private final MainPageService mainPageService;
    private final RequestDisplayService requestDisplayService;

    @Autowired
    public MainPageController(MainPageService mainPageService,
                              RequestDisplayService requestDisplayService) {
        this.mainPageService = mainPageService;
        this.requestDisplayService = requestDisplayService;
    }

    @GetMapping("/main")
    public String showMain(Model model){

        String master_request = "";
        List<RepairRequest> requests = mainPageService.getRequestsByUser();
//        List<String> desc = new ArrayList<>();
//        requests.forEach(el -> desc.add(el.getDescription()));

        if(mainPageService.hasMasterRequest()) {
            master_request = mainPageService.getRequestByMaster().getDescription();
        }

        model.addAttribute("balance", mainPageService.getUserBalance());
        model.addAttribute("master_request", master_request);
        model.addAttribute("user_requests", requests);
        model.addAttribute("paid", RequestState.PAID);
        model.addAttribute("completed", RequestState.COMPLETED);

//        List<RepairRequest> masterRequests = mainPageService.getRequestsByMaster();
//        model.addAttribute("user_requests", desc);

        return "index";
    }

    @PostMapping("main/payment")
    public String payForRequest(PaymentDTO dto){
        mainPageService.setPurchase(dto.getRequestPrice(), dto.getRequestId());
        return "redirect:/main";
    }

    @PostMapping("main/comment")
    public String leaveComment(CommentDTO dto){
        mainPageService.setRequestComment(dto.getComment(), dto.getRequestId());
        return "redirect:/main";
    }

    @PostMapping("/main/edit")
    public String acceptRequest(RequestMasterDTO dto){
        int requestId = mainPageService.getRequestByMaster().getId();
        if(dto.getState().equals(RequestState.ACCEPTED.name()))
            requestDisplayService.setRequestAccepted(requestId);
        if(dto.getPrice() != null)
            requestDisplayService.setRequestPrice(requestId, dto.getPrice());
        if(dto.getState().equals(RequestState.COMPLETED.name()))
            requestDisplayService.setRequestFinished(requestId);

        return "index";
    }
}
