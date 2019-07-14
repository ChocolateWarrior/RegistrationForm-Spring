package de.springboot.service;

import de.springboot.dto.RequestDTO;
import de.springboot.model.RepairRequest;
import de.springboot.model.User;
import de.springboot.repository.RequestRepository;
import de.springboot.repository.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Log4j2
@Service
public class RequestService {

    private final RequestRepository requestRepository;
    private final UserRepository userRepository;

    @Autowired
    public RequestService(RequestRepository requestRepository, UserRepository userRepository) {
        this.requestRepository = requestRepository;
        this.userRepository=userRepository;
    }

    public void pushRequest(RequestDTO dto){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByUsername(authentication.getName());
        RepairRequest requestToAdd = RepairRequest.builder()
                .user(user)
                .type(dto.getType())
                .description(dto.getDescription())
                .requestTime(LocalDateTime.now())
                .build();


        requestRepository.save(requestToAdd);
    }
}
