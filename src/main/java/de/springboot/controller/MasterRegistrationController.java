package de.springboot.controller;

import de.springboot.dto.MasterRegistrationDTO;
import de.springboot.model.Master;
import de.springboot.service.MasterDisplayService;
import de.springboot.service.MasterRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequestMapping("/master-registration")
public class MasterRegistrationController {

    private MasterRegistrationService masterRegistrationService;
    private MasterDisplayService masterDisplayService;

    @Autowired
    MasterRegistrationController(MasterRegistrationService masterRegistrationService,
                                 MasterDisplayService masterDisplayService){
        this.masterRegistrationService=masterRegistrationService;
        this.masterDisplayService= masterDisplayService;
    }

    @GetMapping
    public String getRegistrationForm(Model model){
        model.addAttribute("master", masterDisplayService.getAllMasters());
        return "registration_master";
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void executeRegistration(MasterRegistrationDTO dto){
        System.out.println(dto.toString());
        masterRegistrationService.pushMaster(dto);
    }
}
