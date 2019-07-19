package de.springboot.controller;

import de.springboot.dto.RejectionDTO;
import de.springboot.dto.RequestEditDTO;
import de.springboot.model.RepairRequest;
import de.springboot.model.RequestState;
import de.springboot.service.MasterDisplayService;
import de.springboot.service.RequestDisplayService;
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
    private MasterDisplayService masterDisplayService;

    @Autowired
    public RequestDisplayController(RequestDisplayService requestDisplayService, MasterDisplayService masterDisplayService) {
        this.requestDisplayService = requestDisplayService;
        this.masterDisplayService=masterDisplayService;
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

        masterDisplayService.setMasterRequest(masterDisplayService.getMasterById(dto.getMasterId()), requestDisplayService.getRequestById(requestId));
        if(dto.getMasterId() != 0)
            requestDisplayService.setRequestMaster(requestId, masterDisplayService.getMasterById(dto.getMasterId()));
        if(dto.getPrice() != null)
            requestDisplayService.setRequestPrice(requestId, dto.getPrice());
        if(dto.getState() == RequestState.COMPLETED)
            requestDisplayService.setRequestFinish(requestId);

        return "display_request";
    }

    @GetMapping("/request-display/edit/{id}")
    public String getEditPage(@PathVariable("id") int requestId, Model model){
        model.addAttribute("requestId", requestId);
        return "request_edit";
    }

}
