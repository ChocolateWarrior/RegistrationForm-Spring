package de.springboot.controller;

import de.springboot.dto.MasterRegistrationDTO;
import de.springboot.service.MasterRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequestMapping("/master-registration")
public class MasterRegistrationController {

    private MasterRegistrationService masterRegistrationService;

    @Autowired
    MasterRegistrationController(MasterRegistrationService masterRegistrationService){
        this.masterRegistrationService=masterRegistrationService;
    }

    @GetMapping
    public String getRegistrationForm(){
        return "registration_master";
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void executeRegistration(MasterRegistrationDTO dto){
        masterRegistrationService.pushMaster(dto);
    }
}
