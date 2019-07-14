package de.springboot.controller;

import de.springboot.model.Master;
import de.springboot.service.MasterDisplayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/master-display")
public class MasterDisplayController {

    private final MasterDisplayService masterDisplayService;

    @Autowired
    public MasterDisplayController(MasterDisplayService masterDisplayService) {
        this.masterDisplayService = masterDisplayService;
    }

    @GetMapping
    public List<Master> getListOfMasters(){
        return masterDisplayService.getAllMasters();
    }
}
