package de.springboot.service;

import de.springboot.dto.RegistrationDTO;
import de.springboot.exceptions.LoginMismatchException;
import de.springboot.model.Role;
import de.springboot.model.User;
import de.springboot.repository.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Log4j2
@Service
public class RegistrationService implements UserDetailsService{

    private final UserRepository userRepository;
    private final MessageSource messageSource;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public RegistrationService(UserRepository userRepository,
                       MessageSource messageSource,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.messageSource = messageSource;
        this.passwordEncoder = passwordEncoder;
    }

    public void createUser(RegistrationDTO dto){

        try {
            User userToAdd = User.builder()
                    .firstName(dto.getFirstName())
                    .lastName(dto.getLastName())
                    .enabled(true)
                    .username(dto.getLogin())
                    .password(passwordEncoder.encode(dto.getPassword()))
                    .authorities(Collections.singleton(Role.USER))
                    .build();

            userRepository.save(userToAdd);
            log.info("User saved successfully!");

        } catch(DataIntegrityViolationException e){
            log.error("Login not unique: " + dto.getLogin());
            throw new LoginMismatchException(messageSource.getMessage(
                    "users.registration.login.not_unique",
                    null,
                    LocaleContextHolder.getLocale()) + dto.getLogin(), e);
        }
    }

    public void createMaster(RegistrationDTO dto){

        Set<Role> roles = new HashSet<>();
        roles.add(Role.MASTER);
        roles.add(Role.USER);
        try {
            User userToAdd = User.builder()
                    .firstName(dto.getFirstName())
                    .lastName(dto.getLastName())
                    .enabled(true)
                    .username(dto.getLogin())
                    .password(passwordEncoder.encode(dto.getPassword()))
                    .authorities(roles)
                    .specifications(dto.getSpecifications())
                    .build();

            userRepository.save(userToAdd);
            log.info("Master saved successfully!");

        } catch(DataIntegrityViolationException e){
            log.error("Login not unique: " + dto.getLogin());
            throw new LoginMismatchException(messageSource.getMessage(
                    "users.registration.login.not_unique",
                    null,
                    LocaleContextHolder.getLocale()) + dto.getLogin(), e);
        }
    }

    public void deleteUser(User userToDelete) {
        userRepository.delete(userToDelete);
    }

    public void updateUser(User user) {
        userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(s);

        if (user == null) {
            throw new UsernameNotFoundException(s);
        }

        return user;
    }
}
