//package de.springboot.controller.rest;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/master-display")
//public class MasterDisplayRestController {
//
//    private final MasterDisplayService masterDisplayService;
//
//    @Autowired
//    public MasterDisplayRestController(MasterDisplayService masterDisplayService) {
//        this.masterDisplayService = masterDisplayService;
//    }
//
//    @GetMapping
//    public List<Master> getListOfMasters(){
//        return masterDisplayService.getAllMasters();
//    }
//}
