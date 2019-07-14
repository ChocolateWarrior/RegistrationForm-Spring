package de.springboot.service;

import de.springboot.model.RepairRequest;
import de.springboot.model.User;
import de.springboot.repository.RequestRepository;
import de.springboot.repository.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Log4j2
@Service
public class RequestDisplayService {

    private final RequestRepository requestRepository;
    private final UserRepository userRepository;


    @Autowired
    public RequestDisplayService(RequestRepository requestRepository, UserRepository userRepository) {
        this.requestRepository = requestRepository;
        this.userRepository=userRepository;
    }

    public List<RepairRequest> getAllRequests(){
        List <RepairRequest> res = new ArrayList<>();
        requestRepository.findAll().forEach(res::add);
        return res;
    }

//    public List<RepairRequest> getRequestsByUser(){
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        User user = userRepository.findByLogin(authentication.getName());
//        List<RepairRequest> requests = new ArrayList<>();
//        requests.add(requestRepository.findByUser(user));
//    }
}
