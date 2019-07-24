package de.springboot.controller;

import de.springboot.dto.RejectionDTO;
import de.springboot.dto.RequestEditDTO;
import de.springboot.model.RepairRequest;
import de.springboot.service.RequestDisplayService;
import de.springboot.service.UserDisplayService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@Controller
public class RequestDisplayController {
    private RequestDisplayService requestDisplayService;
    private UserDisplayService userDisplayService;

    @Autowired
    public RequestDisplayController(RequestDisplayService requestDisplayService,
                                    UserDisplayService userDisplayService) {
        this.requestDisplayService = requestDisplayService;
        this.userDisplayService=userDisplayService;
    }

    @GetMapping("/request-display")
    public String showRequests(Model model){
        List<RepairRequest> requests = requestDisplayService.getAllRequests();
        model.addAttribute("all_requests", requests);
        model.addAttribute("request", new RepairRequest());
        return "display_request";
    }

    @PostMapping("/request-display/reject/{id}")
    public String removeRequest(@PathVariable("id") int requestId,
                                Model model, RejectionDTO dto) {
        requestDisplayService.setRequestRejection(requestId, dto.getRejectionMessage());
        model.addAttribute("all_requests", requestDisplayService.getAllRequests());
        return "display_request";
    }


    @PostMapping("/request-display/edit/{id}")
    public String editRequest(@PathVariable("id") int requestId, RequestEditDTO dto) {

        userDisplayService.setMasterRequest(userDisplayService.getUserById(dto.getMasterId()), requestDisplayService.getRequestById(requestId));
        if(dto.getMasterId() != 0)
            requestDisplayService.setRequestMaster(requestId, userDisplayService.getUserById(dto.getMasterId()));


        return "display_request";
    }

    @GetMapping("/request-display/edit/{id}")
    public String getEditPage(@PathVariable("id") int requestId, Model model){
        model.addAttribute("requestId", requestId);
        return "request_edit";
    }

}
