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

    @GetMapping("/navbar")
    public String showNavbar() {
        return "responsive_navbar";
    }

    @GetMapping("/test")
    public String showTest() {
        return "test";
    }

    @GetMapping("/error")
    public String handleError() {
        log.warn("Error occurred");
        return "error";
    }
}
