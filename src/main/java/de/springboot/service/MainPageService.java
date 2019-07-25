package de.springboot.service;

import de.springboot.model.RepairRequest;
import de.springboot.model.User;
import de.springboot.repository.RequestRepository;
import de.springboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MainPageService {

    private final RequestRepository requestRepository;
    private final UserRepository userRepository;

    @Autowired
    public MainPageService(RequestRepository requestRepository, UserRepository userRepository) {
        this.requestRepository = requestRepository;
        this.userRepository = userRepository;
    }

    public List<RepairRequest> getRequestsByUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByUsername(authentication.getName());
        return requestRepository.findByUser(user);
    }

    public boolean hasMasterRequest(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByUsername(authentication.getName());
        return user.getMasterRequest() != null;
    }

    public RepairRequest getRequestByMaster(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByUsername(authentication.getName());
        return requestRepository.findById(user.getMasterRequest().getId());
    }

//    public List<RepairRequest> getRequestsByMaster(){
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        User user = userRepository.findByUsername(authentication.getName());
//        Set<RepairRequest> masterRequests= new HashSet<>()
//
//        return requestRepository.findById(user.getMasterRequests());
//    }

}
