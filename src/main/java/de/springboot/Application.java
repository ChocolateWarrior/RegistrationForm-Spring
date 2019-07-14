package de.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@SpringBootApplication
public class Application {
    public static void main(String[] args) throws Exception{
        SpringApplication.run(Application.class, args);
    }

//    @GetMapping("/main")
//    public String showMain(){return "index";}

    @GetMapping("/")
    public String showDefault(){return "index";}

    @GetMapping("/display")
    public String showUsers() {
        return "display";
    }

    @GetMapping("/master-display")
    public String showMasters() {
        return "display_master";
    }

    @GetMapping("/request-display")
    public String showRequests() {
        return "display_request";
    }

}
