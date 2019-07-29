package de.springboot.controller;

import de.springboot.dto.RegistrationDTO;
import de.springboot.exceptions.LoginNotUniqueException;
import de.springboot.model.Specification;
import de.springboot.service.RegistrationService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Log4j2
@Controller
@RequestMapping("/registration")
public class RegistrationController
        implements ErrorController
{

    private RegistrationService registrationService;
    private MessageSource messageSource;

    @Autowired
    public RegistrationController(RegistrationService registrationService, MessageSource messageSource) {
        this.registrationService = registrationService;
        this.messageSource=messageSource;
    }

    @GetMapping
    public String getRegistrationForm(Model model){
        model.addAttribute("all_specifications", Specification.values());
        return "registration";
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public String executeRegistration(RegistrationDTO dto, Model model) throws LoginNotUniqueException {
        model.addAttribute("all_specifications", Specification.values());
        model.addAttribute("message", messageSource.getMessage("reg.success",
                null,
                LocaleContextHolder.getLocale()));


        if(!(dto.getSpecifications()==null)){
            registrationService.createMaster(dto);
            log.info("Created master");
        }
        else {
            registrationService.createUser(dto);
            log.info("Created user");
        }

        return "registration";
    }

    @ExceptionHandler(LoginNotUniqueException.class)
    public String handleRuntimeException(Model model, LoginNotUniqueException exception) {
        model.addAttribute("error_message", exception.getMessage());
        model.addAttribute("all_specifications", Specification.values());

        return "registration";
    }



    @Override
    public String getErrorPath() {
        return "/error";
    }

}
