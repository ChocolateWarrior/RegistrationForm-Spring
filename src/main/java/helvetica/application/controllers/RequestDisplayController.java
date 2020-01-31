package helvetica.application.controllers;

import helvetica.application.dtos.RejectionDTO;
import helvetica.application.dtos.RequestEditDTO;
import helvetica.application.entities.RepairRequest;
import helvetica.application.entities.RequestState;
import helvetica.application.entities.Specification;
import helvetica.application.services.RequestService;
import helvetica.application.services.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Log4j2
@Controller
@RequestMapping("/request-display")
public class RequestDisplayController {
    private RequestService requestService;
    private UserService userService;

    @Autowired
    public RequestDisplayController(RequestService requestService,
                                    UserService userService) {
        this.requestService = requestService;
        this.userService=userService;
    }

    @GetMapping
    public String showRequests(Model model,
                               @RequestParam("page")Optional<Integer> page,
                               @RequestParam("size")Optional<Integer> size){

        int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);

        Page<RepairRequest> requestPage = requestService.findPaginated(PageRequest.of(currentPage - 1, pageSize));

        model.addAttribute("requestPage", requestPage);

        int totalPages = requestPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        List<RepairRequest> requests = requestService.getAllRequests();
        model.addAttribute("all_requests", requests);
        model.addAttribute("request", new RepairRequest());
        model.addAttribute("completed", RequestState.COMPLETED);
        model.addAttribute("paid", RequestState.PAID);
        model.addAttribute("accepted", RequestState.ACCEPTED);
        model.addAttribute("rejected", RequestState.REJECTED);
        return "display_request";
    }

    @PostMapping("/reject/{id}")
    public String removeRequest(@PathVariable("id") int requestId,
                                Model model, RejectionDTO dto) {
        requestService.setRequestRejection(requestId, dto.getRejectionMessage());
        model.addAttribute("all_requests", requestService.getAllRequests());
        return "redirect:/request-display";
    }

    @PostMapping("/edit/{id}")
    public String editRequest(@PathVariable("id") int requestId, RequestEditDTO dto) {

        userService.addMasterRequest(userService.getByUsername(dto.getMasterUsername()),
                requestService.getRequestById(requestId));
        if(Objects.nonNull(dto.getPrice()))
            requestService.setRequestPrice(requestId, dto.getPrice());
        if(Objects.nonNull(dto.getMasterUsername()))
            requestService.addRequestMaster(requestId, userService.getByUsername(dto.getMasterUsername()));
        else return "request_edit";

        return "redirect:/request-display";
    }

    @GetMapping("/edit/{id}")
    public String getEditPage(@PathVariable("id") int requestId, Model model){
        model.addAttribute("requestId", requestId);
        RepairRequest request = requestService.getRequestById(requestId);
        String spec = request.getSpecification().toUpperCase().replace(" ", "_");
        model.addAttribute("masters", userService.getMastersBySpecification(
                Specification.valueOf(spec)));

        return "request_edit";
    }

}
