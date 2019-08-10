package de.springboot.service;

import de.springboot.model.RepairRequest;
import de.springboot.model.RequestState;
import de.springboot.model.User;
import de.springboot.repository.RequestRepository;
import de.springboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
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

    public boolean hasMasterRequests(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByUsername(authentication.getName());
        return user.getMasterRequests() != null;
    }

    public List<RepairRequest> getRequestsByMaster(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByUsername(authentication.getName());
        return requestRepository.findByMasters(user);
    }

    public BigDecimal getUserBalance(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByUsername(authentication.getName());
        return user.getBalance();
    }

    @Transactional
    public void addToUserBalance(BigDecimal value){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByUsername(authentication.getName());
        if(user.getBalance() != null)
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

    public void setRequestComment(String comment, int requestId){
        RepairRequest request = requestRepository.findById(requestId);
        request.setComment(comment);
        requestRepository.save(request);
    }

}
