package de.springboot.controller;

import de.springboot.dto.RejectionDTO;
import de.springboot.dto.RequestEditDTO;
import de.springboot.model.RepairRequest;
import de.springboot.model.RequestState;
import de.springboot.model.Specification;
import de.springboot.service.RequestDisplayService;
import de.springboot.service.UserDisplayService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Objects;

@Log4j2
@Controller
public class RequestDisplayController {
    private RequestDisplayService requestDisplayService;
    private UserDisplayService userDisplayService;

    @Autowired
    public RequestDisplayController(RequestDisplayService requestDisplayService,
                                    UserDisplayService userDisplayService) {
        this.requestDisplayService = requestDisplayService;
        this.userDisplayService=userDisplayService;
    }

    @GetMapping("/request-display")
    public String showRequests(Model model){
        List<RepairRequest> requests = requestDisplayService.getAllRequests();
        model.addAttribute("all_requests", requests);
        model.addAttribute("request", new RepairRequest());
        model.addAttribute("completed", RequestState.COMPLETED);
        model.addAttribute("paid", RequestState.PAID);
        model.addAttribute("accepted", RequestState.ACCEPTED);
        model.addAttribute("rejected", RequestState.REJECTED);
        return "display_request";
    }

    @PostMapping("/request-display/reject/{id}")
    public String removeRequest(@PathVariable("id") int requestId,
                                Model model, RejectionDTO dto) {
        requestDisplayService.setRequestRejection(requestId, dto.getRejectionMessage());
        model.addAttribute("all_requests", requestDisplayService.getAllRequests());
        return "redirect:/request-display";
    }


    @PostMapping("/request-display/edit/{id}")
    public String editRequest(@PathVariable("id") int requestId, RequestEditDTO dto) {

        userDisplayService.addMasterRequest(userDisplayService.getByUsername(dto.getMasterUsername()),
                requestDisplayService.getRequestById(requestId));
        if(Objects.nonNull(dto.getPrice()))
            requestDisplayService.setRequestPrice(requestId, dto.getPrice());
        if(Objects.nonNull(dto.getMasterUsername()))
            requestDisplayService.addRequestMaster(requestId, userDisplayService.getByUsername(dto.getMasterUsername()));
        else return "request_edit";

        return "redirect:/request-display";
    }


    @GetMapping("/request-display/edit/{id}")
    public String getEditPage(@PathVariable("id") int requestId, Model model){
        model.addAttribute("requestId", requestId);
        RepairRequest request = requestDisplayService.getRequestById(requestId);
        String spec = request.getSpecification().toUpperCase().replace(" ", "_");
        System.out.println(spec);
        model.addAttribute("masters", userDisplayService.getMastersBySpecification(
                Specification.valueOf(spec)));

        return "request_edit";
    }

}
