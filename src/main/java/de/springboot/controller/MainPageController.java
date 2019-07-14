package de.springboot.controller;

import de.springboot.model.RepairRequest;
import de.springboot.service.MainPageService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Log4j2
@Controller
@RequestMapping("/main")
public class MainPageController {

    private final MainPageService mainPageService;

    @Autowired
    public MainPageController(MainPageService mainPageService) {
        this.mainPageService = mainPageService;
    }

    @GetMapping
    public String showMain(Model model){
        List<RepairRequest> requests = mainPageService.getRequestsByUser();
//        log.info(requests.toString());
        model.addAttribute("user_requests", requests);
        model.addAttribute("request", new RepairRequest());
        return "index";
    }
}
