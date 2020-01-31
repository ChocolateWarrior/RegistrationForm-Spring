package helvetica.application.services;

import helvetica.application.dtos.LoginDTO;
import helvetica.application.dtos.RegistrationDTO;
import helvetica.application.exceptions.CredentialsException;
import helvetica.application.exceptions.LoginNotUniqueException;
import helvetica.application.entities.*;
import helvetica.application.repositories.RequestRepository;
import helvetica.application.repositories.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.*;

@Log4j2
@Service
public class UserService implements UserDetailsService{

    private final UserRepository userRepository;
    private final RequestRepository requestRepository;
    private final MessageSource messageSource;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository,
                       RequestRepository requestRepository,
                       MessageSource messageSource,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.requestRepository = requestRepository;
        this.messageSource = messageSource;
        this.passwordEncoder = passwordEncoder;
    }

    public void createUser(RegistrationDTO dto) {

        try{
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

        }catch(DataIntegrityViolationException exception){
            log.warn("Login not unique: " + dto.getLogin());
            throw new LoginNotUniqueException(messageSource.getMessage(
                    "reg.login_not_unique",
                    null,
                    LocaleContextHolder.getLocale()) + dto.getLogin(), exception);
        }
    }

    public void createMaster(RegistrationDTO dto) throws LoginNotUniqueException {

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
            throw new LoginNotUniqueException(messageSource.getMessage(
                    "reg.login_not_unique",
                    null,
                    LocaleContextHolder.getLocale()) + dto.getLogin(), e);
        }
    }

    public boolean isDuplicate(String username){
        return userRepository.findByUsername(username) != null;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException(username);
        }

        return user;
    }

    public User getUser(LoginDTO dto) throws CredentialsException {
        User user = userRepository.findByUsernameAndPassword(dto.getLogin(), dto.getPassword());

        if(Objects.isNull(user)) {
            log.warn(dto + " there is no such user record in database");
            throw new CredentialsException("Invalid credentials");
        }

        if (!dto.getPassword().equals(user.getPassword())) {
            log.warn(dto + " incorrect password");
            throw new CredentialsException("Invalid credentials");
        }

        log.info(dto + " user successfully logged in");
        return user;
    }

    public List<User> getAllUsers(){
        return new ArrayList<>(userRepository.findAll(Sort.by(Sort.Direction.ASC, "id")));
    }

    private User getUserById(int id){
        return userRepository.findById(id);
    }

    public User getByUsername(String username){
        return userRepository.findByUsername(username);
    }

    public List<User> getMastersBySpecification(Specification specification){
        return userRepository.findAllBySpecifications(specification);
    }

    public void setUserFirstName(int userId, String firstName){
        User user = getUserById(userId);
        user.setFirstName(firstName);
        userRepository.save(user);
    }

    public void setUserLastName(int userId, String lastName){
        User user = getUserById(userId);
        user.setLastName(lastName);
        userRepository.save(user);
    }

    public void setUserLogin(int userId, String login){
        User user = getUserById(userId);
        user.setUsername(login);
        userRepository.save(user);
    }

    public void setUserPassword(int userId, String password){
        User user = getUserById(userId);
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);
    }

    public void removeUser(int userId){
        User user = getUserById(userId);
        userRepository.delete(user);
    }

    public void addMasterRequest(User master, RepairRequest request){
        master.addMasterRequest(request);
        userRepository.save(master);
    }

    public BigDecimal getUserBalance(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByUsername(authentication.getName());
        return user.getBalance();
    }

    public void addToUserBalance(BigDecimal value){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByUsername(authentication.getName());
        if(Objects.nonNull(user.getBalance()))
            user.setBalance(user.getBalance().add(value));
        else
            user.setBalance(value);
        userRepository.save(user);
    }

    @Transactional
    public void setPurchase(BigDecimal price, int requestId){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByUsername(authentication.getName());
        user.setBalance(user.getBalance().subtract(price));
        userRepository.save(user);

        RepairRequest request = requestRepository.findById(requestId);
        request.setState(RequestState.PAID);
        requestRepository.save(request);

    }

}
