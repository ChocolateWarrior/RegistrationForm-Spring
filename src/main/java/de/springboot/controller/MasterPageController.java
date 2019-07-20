package de.springboot.controller;

import de.springboot.model.RepairRequest;
import de.springboot.service.MasterPageService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Log4j2
@Controller
@RequestMapping("/main/master")
public class MasterPageController {

    private final MasterPageService masterPageService;

    @Autowired
    public MasterPageController(MasterPageService masterPageService) {
        this.masterPageService = masterPageService;
    }

    @GetMapping
    public String showMasterPage(Model model){
        RepairRequest request = masterPageService.getRequestByMaster();
        model.addAttribute("master_request", request.toString());
        return "index_master";
    }

}
