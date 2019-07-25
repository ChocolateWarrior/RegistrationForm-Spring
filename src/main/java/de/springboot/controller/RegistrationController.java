package de.springboot.controller;

import de.springboot.dto.RegistrationDTO;
import de.springboot.model.Specification;
import de.springboot.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequestMapping("/registration")
public class RegistrationController  {

    private RegistrationService registrationService;

    @Autowired
    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @GetMapping
    public String getRegistrationForm(Model model){

        model.addAttribute("all_specifications", Specification.values());
        model.addAttribute("master", new RegistrationDTO());
        return "registration";
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void executeRegistration(RegistrationDTO dto, Model model){

        model.addAttribute("message", "Successfully registered");
        model.addAttribute("master", dto);


        if(!dto.getSpecifications().isEmpty()){
            System.out.println("MASTER");
            registrationService.createMaster(dto);
        }
        else {
            System.out.println("USER");
            registrationService.createUser(dto);
        }
    }

}
