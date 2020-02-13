package helvetica.application.controllers;

import helvetica.application.dtos.CommentDTO;
import helvetica.application.dtos.PaymentDTO;
import helvetica.application.dtos.RequestMasterDTO;
import helvetica.application.entities.RepairRequest;
import helvetica.application.entities.RequestState;
import helvetica.application.services.RequestService;
import helvetica.application.services.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Log4j2
@Controller
@RequestMapping("/main")

public class MainPageController {

    private final UserService userService;
    private final RequestService requestService;

    @Autowired
    public MainPageController(UserService userService,
                              RequestService requestService) {
        this.userService = userService;
        this.requestService = requestService;
    }

    @GetMapping
    public String showMain(Model model,
                           @RequestParam("page") Optional<Integer> page,
                           @RequestParam("size")Optional<Integer> size){

        int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);

        List<Integer> sizesList = new ArrayList<>(Arrays.asList(5, 10, 15, 20));
        model.addAttribute("pageSizes", sizesList);

        Page<RepairRequest> userRequestPage = requestService
                .findPaginatedByUser(PageRequest.of(currentPage - 1, pageSize));
        Page<RepairRequest> masterRequestPage = requestService
                .findPaginatedByMaster(PageRequest.of(currentPage - 1, pageSize));

        addPaginationToModel(model, userRequestPage, "userRequestPage", "userPageNumbers");
        addPaginationToModel(model, masterRequestPage, "masterRequestPage", "masterPageNumbers");

        List<RepairRequest> master_requests = requestService.getRequestsByMaster();
        List<RepairRequest> requests = requestService.getRequestsByUser();

        model.addAttribute("balance", userService.getUserBalance());
        model.addAttribute("master_requests", master_requests);
        model.addAttribute("user_requests", requests);
        model.addAttribute("paid", RequestState.PAID);
        model.addAttribute("completed", RequestState.COMPLETED);
        model.addAttribute("accepted", RequestState.ACCEPTED);

        return "index";
    }

    @PostMapping("/payment")
    public String payForRequest(PaymentDTO dto, Model model){
        userService.setPurchase(dto.getRequestPrice(), dto.getRequestId());
        return "redirect:/main";
    }

    @PostMapping("/comment")
    public String leaveComment(CommentDTO dto){
        requestService.setRequestComment(dto.getComment(), dto.getRequestId());
        return "redirect:/main";
    }

    @PostMapping("/complete")
    public String completeRequest(RequestMasterDTO dto){
        requestService.setRequestComplete(dto.getRequestId());
        return "index";
    }

    private void addPaginationToModel(Model model,
                                      Page<RepairRequest> requestPage,
                                      String pageAttributeName,
                                      String numberAttributeName) {
        model.addAttribute(pageAttributeName, requestPage);

        int totalPages = requestPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute(numberAttributeName, pageNumbers);
        }
    }
}
