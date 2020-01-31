package helvetica.application.services;

import helvetica.application.dtos.RegistrationDTO;
import helvetica.application.entities.Role;
import helvetica.application.entities.User;
import helvetica.application.repositories.UserRepository;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private PasswordEncoder passwordEncoder;

//    @Ignore
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

//    @Ignore
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

//    @Ignore
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

//    @Ignore
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

//    @Ignore
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