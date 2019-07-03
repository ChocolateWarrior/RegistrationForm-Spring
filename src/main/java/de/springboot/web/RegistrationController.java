package de.springboot.web;

import de.springboot.dto.DTO;
import de.springboot.model.User;
import de.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/registration")
public class RegistrationController  {

    private UserService userService;

    @Autowired
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(method = RequestMethod.POST)
    public void executeRegistration(DTO dto){
        User userToAdd = new User(dto.getFirstName(),
                dto.getLastName(),
                dto.getLogin(),
                dto.getPassword());
        userService.pushUser(userToAdd);
    }

//    @ExceptionHandler(RuntimeException.class)
//    public ResponseEntity<String> handleRuntimeException(RuntimeException ex) {
////        log.warn(ex.getMessage());
//        return ResponseEntity
//                .status(HttpStatus.BAD_REQUEST)
//                .body("{\"message\": \"" + ex.getMessage() + "\"}");
//    }

}
