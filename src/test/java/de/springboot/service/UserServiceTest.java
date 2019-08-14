package de.springboot.service;

import de.springboot.dto.RegistrationDTO;
import de.springboot.model.Role;
import de.springboot.model.User;
import de.springboot.repository.RequestRepository;
import de.springboot.repository.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.MessageSource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Collections;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @Test
    public void createUser() {
        RegistrationDTO dto = RegistrationDTO.builder()
                .firstName("firstname")
                .lastName("lastname")
                .login("login")
                .password("password")
                .build();

        User user = User.builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .enabled(true)
                .username(dto.getLogin())
                .password(passwordEncoder.encode(dto.getPassword()))
                .authorities(Collections.singleton(Role.USER))
                .build();

        userService.createUser(dto);

        Mockito.verify(userRepository, Mockito.times(1)).save(user);
        Mockito.verify(passwordEncoder,Mockito.times(2)).encode(dto.getPassword());

    }


    @Test
    public void removeUser(){
        User user = User.builder()
                .id(1)
                .firstName("firstname")
                .lastName("lastname")
                .enabled(true)
                .username("login")
                .password(passwordEncoder.encode("password"))
                .authorities(Collections.singleton(Role.USER))
                .build();

        Mockito.doReturn(user)
                .when(userRepository)
                .findById(1);

        userService.removeUser(user.getId());

        Mockito.verify(userRepository, Mockito.times(1)).delete(user);
    }

    @Test
    public void setUserLastName() {

        User user = User.builder()
                .id(1)
                .firstName("firstname")
                .lastName("lastname")
                .enabled(true)
                .username("login")
                .password(passwordEncoder.encode("password"))
                .authorities(Collections.singleton(Role.USER))
                .build();

        Mockito.doReturn(user)
                .when(userRepository)
                .findById(1);

        userService.setUserLastName(1,"lastname");
        Mockito.verify(userRepository, Mockito.times(1)).save(user);


    }

    @Test
    public void setUserLogin() {

        User user = User.builder()
                .id(1)
                .firstName("firstname")
                .lastName("lastname")
                .enabled(true)
                .username("login")
                .password(passwordEncoder.encode("password"))
                .authorities(Collections.singleton(Role.USER))
                .build();

        Mockito.doReturn(user)
                .when(userRepository)
                .findById(1);

        userService.setUserLogin(1,"login");
        Mockito.verify(userRepository, Mockito.times(1)).save(user);

    }

    @Test
    public void setUserPassword() {

        User user = User.builder()
                .id(1)
                .firstName("firstname")
                .lastName("lastname")
                .enabled(true)
                .username("login")
                .password(passwordEncoder.encode("password"))
                .authorities(Collections.singleton(Role.USER))
                .build();

        Mockito.doReturn(user)
                .when(userRepository)
                .findById(1);

        userService.setUserPassword(1,"password");
        Mockito.verify(userRepository, Mockito.times(1)).save(user);
        Mockito.verify(passwordEncoder,Mockito.times(2)).encode("password");

    }
}