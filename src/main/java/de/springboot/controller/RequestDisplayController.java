package de.springboot.controller;

import de.springboot.model.RepairRequest;
import de.springboot.service.RequestDisplayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/request-display")
public class RequestDisplayController {
    private RequestDisplayService requestDisplayService;

    @Autowired
    public RequestDisplayController(RequestDisplayService requestDisplayService) {
        this.requestDisplayService = requestDisplayService;
    }
    @GetMapping
    public List<RepairRequest> getListOfRequests(){
        return requestDisplayService.getAllRequests();
    }

}
