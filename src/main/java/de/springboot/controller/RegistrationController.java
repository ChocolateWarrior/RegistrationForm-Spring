package de.springboot.controller;

import de.springboot.dto.RegistrationDTO;
import de.springboot.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/registration")
public class RegistrationController  {

    private RegistrationService registrationService;

    @Autowired
    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @GetMapping
    public String getRegistrationForm(){
        return "registration";
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void executeRegistration(RegistrationDTO dto){
        registrationService.createUser(dto);
    }

}
