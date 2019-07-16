package de.springboot.controller;

import de.springboot.dto.RequestDTO;
import de.springboot.dto.RequestEditDTO;
import de.springboot.model.Master;
import de.springboot.model.RepairRequest;
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
@RequestMapping
public class ReqDisplayController {
    private RequestDisplayService requestDisplayService;
    private MasterDisplayService masterDisplayService;

    @Autowired
    public ReqDisplayController(RequestDisplayService requestDisplayService, MasterDisplayService masterDisplayService) {
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

    @PostMapping("/request-display/remove/{id}")
    public String removeRequest(@PathVariable("id") int requestId,
                                Model model) {
        RepairRequest request = requestDisplayService.getRequestById(requestId);
        requestDisplayService.removeRequest(request);
        model.addAttribute("all_requests", requestDisplayService.getAllRequests());
        return "display_request";
    }

    @PostMapping("/request-display/set-master/{id}")
    public String setRequestMaster(@PathVariable("id") int requestId,
                                   Model model, RequestEditDTO dto) {

        requestDisplayService.setRequestMaster(requestId, masterDisplayService.getMasterById(dto.getMasterId()));
        requestDisplayService.setRequestPrice(requestId, dto.getPrice());
        requestDisplayService.setRequestFinish(requestId);

        return "display_request";
    }

    @GetMapping("/request-display/set-master/{id}")
    public String getEditPage(@PathVariable("id") int requestId, Model model){
        model.addAttribute("requestId", requestId);
        return "request_edit";
    }

}
