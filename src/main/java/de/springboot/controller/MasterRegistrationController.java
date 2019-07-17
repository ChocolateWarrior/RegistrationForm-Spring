package de.springboot.controller;

import de.springboot.dto.MasterRegistrationDTO;
import de.springboot.model.Master;
import de.springboot.model.Specification;
import de.springboot.service.MasterDisplayService;
import de.springboot.service.MasterRegistrationService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Log4j2
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
        model.addAttribute("master", new MasterRegistrationDTO());
        model.addAttribute("all_specifications", Specification.values());
        return "registration_master";
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public String executeRegistration(MasterRegistrationDTO dto, Model model){
        log.info(dto);
        masterRegistrationService.pushMaster(dto);
        model.addAttribute("message", "Successfully registered");
        model.addAttribute("master", dto);
        model.addAttribute("all_specifications", Specification.values());
        return "registration_master";
    }

}
