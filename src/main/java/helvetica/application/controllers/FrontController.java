package helvetica.application.controllers;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Log4j2
@Controller
public class FrontController {

    @GetMapping("/")
    public String showDefault() {
        return "index";
    }

    @GetMapping("/error")
    public String handleError() {
        log.warn("Error occurred");
        return "error";
    }
}
