package de.springboot.controller;

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

import java.util.ArrayList;
import java.util.List;

@Log4j2
@Controller
//@RequestMapping("/main")
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

//        if(mainPageService.getUserBalance() != null)
        model.addAttribute("balance", mainPageService.getUserBalance());

        List<RepairRequest> requests = mainPageService.getRequestsByUser();
        List<String> desc = new ArrayList<>();
        requests.forEach(el -> desc.add(el.getDescription()));


        String master_request = "";

        if(mainPageService.hasMasterRequest())
            master_request = mainPageService.getRequestByMaster().getDescription();

        model.addAttribute("master_request", master_request);

//        List<RepairRequest> masterRequests = mainPageService.getRequestsByMaster();
//        model.addAttribute("user_requests", desc);
        model.addAttribute("user_requests", requests);


        return "index";
    }

//    @PostMapping("main/payment")
//    public String payForRequest(Model model){
//
//
//    }

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
