package de.springboot;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Log4j2
@Controller
@SpringBootApplication
public class Application {
    public static void main(String[] args) throws Exception{
        SpringApplication.run(Application.class, args);
    }

    @GetMapping("/")
    public String showDefault(){return "redirect:/main";}

    @GetMapping("/error")
    public String handleError() {
        log.warn("Error occurred");
        return "error";
    }

}


