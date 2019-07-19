package de.springboot.controller;

import de.springboot.dto.MasterRegistrationDTO;
import de.springboot.model.Master;
import de.springboot.service.MasterDisplayService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Log4j2
@Controller
public class MasterDisplayController {

    private final MasterDisplayService masterDisplayService;

    @Autowired
    public MasterDisplayController(MasterDisplayService masterDisplayService) {
        this.masterDisplayService = masterDisplayService;
    }

    @GetMapping("/master-display")
    public String getListOfMasters(Model model){
        List<Master> masters = masterDisplayService.getAllMasters();
        model.addAttribute("all_masters", masters);
        model.addAttribute("master", new Master());
        return "display_master";
    }

    @PostMapping("/master-display/remove/{id}")
    public String removeMaster(@PathVariable("id") int masterId){
        masterDisplayService.removeMaster(masterId);
        return "display_master";
    }

    @PostMapping("/master-display/edit/{id}")
    public String editMaster(@PathVariable("id") int masterId,
                           MasterRegistrationDTO dto) {

        if(!dto.getFirstName().equals(""))
            masterDisplayService.setMasterFirstName(masterId, dto.getFirstName());
        if(!dto.getLastName().equals(""))
            masterDisplayService.setMasterLastName(masterId, dto.getLastName());
        if(!dto.getLogin().equals(""))
            masterDisplayService.setMasterLogin(masterId, dto.getLogin());
        if(!dto.getPassword().equals(""))
            masterDisplayService.setMasterPassword(masterId,new BCryptPasswordEncoder().encode(dto.getPassword()));
        return "master_edit";
    }

    @GetMapping("/master-display/edit/{id}")
    public String getEditPage(@PathVariable("id") int masterId, Model model){
        model.addAttribute("masterId", masterId);
        return "master_edit";
    }
}
