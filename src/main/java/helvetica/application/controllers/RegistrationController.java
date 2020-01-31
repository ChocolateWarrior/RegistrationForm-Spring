package helvetica.application.controllers;

import helvetica.application.dtos.RegistrationDTO;
import helvetica.application.entities.Specification;
import helvetica.application.services.UserService;
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
public class RegistrationController implements ErrorController {

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

        model.addAttribute("all_specifications", Specification.values());

        if (checkIfLoginIsDuplicated(dto, model)) return "registration";
        if (bindingResult.hasErrors()) return "registration";
        createUserOrMaster(dto);

        model.addAttribute("message", messageSource.getMessage("reg.success",
                null,
                LocaleContextHolder.getLocale()));
        return "registration";
    }

    private void createUserOrMaster(@ModelAttribute("user") @Valid RegistrationDTO dto) {
        if(dto.getSpecifications().isEmpty()){
            userService.createUser(dto);
            log.info("Created user");
        }
        else {
            userService.createMaster(dto);
            log.info("Created master");
        }
    }

    private boolean checkIfLoginIsDuplicated(@ModelAttribute("user") @Valid RegistrationDTO dto, Model model) {
        if(userService.isDuplicate(dto.getLogin())){
            model.addAttribute("error_message", messageSource.getMessage("reg.login_not_unique",
                    null,
                    LocaleContextHolder.getLocale()) + dto.getLogin());
            model.addAttribute("all_specifications", Specification.values());
            return true;
        }
        return false;
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }

}
