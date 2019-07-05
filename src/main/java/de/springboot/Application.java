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

    @GetMapping("/main")
    public String showMain(){return "index";}
//
    @GetMapping("/display")
    public String showUsers() {
        return "display";
    }

//    @GetMapping("/registration")
//    public String showRegistrationForm() {
//        return "registration";
//    }

//    @GetMapping("/login")
//    public String showLogin() {
//        return "login";
//    }
}
