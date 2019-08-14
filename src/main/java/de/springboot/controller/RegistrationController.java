package de.springboot.controller;

import de.springboot.dto.RegistrationDTO;
import de.springboot.model.Specification;
import de.springboot.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Log4j2
@Controller
@RequestMapping("/registration")
public class RegistrationController
        implements ErrorController
{

    private UserService userService;
    private MessageSource messageSource;

    @Autowired
    public RegistrationController(UserService userService, MessageSource messageSource) {
        this.userService = userService;
        this.messageSource = messageSource;
    }

    @GetMapping
    public String getRegistrationForm(@ModelAttribute("user") RegistrationDTO dto, Model model){
        model.addAttribute("all_specifications", Specification.values());
        return "registration";
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public String executeRegistration(@ModelAttribute("user") @Valid RegistrationDTO dto,
                                      BindingResult bindingResult, Model model){

        if(userService.isDuplicate(dto.getLogin())){
            model.addAttribute("error_message", messageSource.getMessage("reg.login_not_unique",
                    null,
                    LocaleContextHolder.getLocale()) + dto.getLogin());
            model.addAttribute("all_specifications", Specification.values());
            return "registration";
        }

        model.addAttribute("all_specifications", Specification.values());
        if (bindingResult.hasErrors()) {
            return "registration";
        }

        if(!(dto.getSpecifications()==null)){
            userService.createMaster(dto);
            log.info("Created master");
        }
        else {
            userService.createUser(dto);
            log.info("Created user");
        }

        model.addAttribute("message", messageSource.getMessage("reg.success",
                null,
                LocaleContextHolder.getLocale()));

        return "registration";
    }


    @Override
    public String getErrorPath() {
        return "/error";
    }

}
