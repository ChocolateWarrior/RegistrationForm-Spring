package de.springboot.controller;

import de.springboot.dto.RequestDTO;
import de.springboot.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequestMapping("/request")
public class RequestController {

    private RequestService requestService;

    @Autowired
    RequestController(RequestService requestService){
        this.requestService = requestService;
    }

    @GetMapping
    public String getRegistrationForm(){
        return "request";
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void executeRegistration(RequestDTO dto){
        requestService.pushRequest(dto);
    }

}
