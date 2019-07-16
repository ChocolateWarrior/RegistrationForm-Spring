package de.springboot.controller;

import de.springboot.dto.RequestDTO;
import de.springboot.model.RepairRequest;
import de.springboot.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
//@RequestMapping("/request")
public class RequestController {

    private RequestService requestService;

    @Autowired
    RequestController(RequestService requestService){
        this.requestService = requestService;
    }

    @GetMapping("/request")
    public String getRegistrationForm(){
        return "request";
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/request")
    public void executeRegistration(RequestDTO dto){
        requestService.pushRequest(dto);
    }

//    @PostMapping("/request-remove/{id}")
//    public String deleteRequest(@PathVariable("id") int requestId,
//                                 Model model) {
//        RepairRequest request = requestService.getRequestById(requestId);
//        requestService.removeRequest(request);
//        model.addAttribute("requests", requestService.getAll());
//        return "request";
//    }

}
