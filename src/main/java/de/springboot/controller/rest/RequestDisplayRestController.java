package de.springboot.controller.rest;

import de.springboot.model.RepairRequest;
import de.springboot.service.RequestDisplayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/request-display")
public class RequestDisplayRestController {
    private RequestDisplayService requestDisplayService;

    @Autowired
    public RequestDisplayRestController(RequestDisplayService requestDisplayService) {
        this.requestDisplayService = requestDisplayService;
    }
    @GetMapping
    public List<RepairRequest> getListOfRequests(){
        return requestDisplayService.getAllRequests();
    }

    @GetMapping("/request-display")
    public String showUsers(Model model){
        List<RepairRequest> requests = requestDisplayService.getAllRequests();
//        log.info(requests.toString());
        model.addAttribute("all_requests", requests);
        model.addAttribute("request", new RepairRequest());
        return "display_request";
    }
}
