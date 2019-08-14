package de.springboot.service;

import de.springboot.dto.RequestDTO;
import de.springboot.model.RepairRequest;
import de.springboot.model.RequestState;
import de.springboot.model.User;
import de.springboot.repository.RequestRepository;
import de.springboot.repository.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
                .specification(dto.getSpecification())
                .description(dto.getDescription())
                .requestTime(LocalDateTime.now())
                .build();


        requestRepository.save(requestToAdd);
    }

    public List<RepairRequest> getAllRequests(){
        List <RepairRequest> res = new ArrayList<>();
        requestRepository.findAll().forEach(res::add);
        return res;
    }

    public RepairRequest getRequestById(int requestId){
        return requestRepository.findById(requestId);
    }


    public void addRequestMaster(int requestId, User master){
        RepairRequest request = getRequestById(requestId);
        request.addMaster(master);
        requestRepository.save(request);
    }

    public void setRequestComplete(int requestId){
        RepairRequest request = getRequestById(requestId);
        request.setState(RequestState.COMPLETED);
        requestRepository.save(request);
    }

    public void setRequestPrice(int requestId, BigDecimal price){
        RepairRequest request = getRequestById(requestId);
        request.setPrice(price);
        requestRepository.save(request);
    }

    public void setRequestRejection(int requestId, String message){
        RepairRequest request = getRequestById(requestId);
        request.setState(RequestState.REJECTED);
        request.setRejectionMessage(message);
        requestRepository.save(request);
    }

    public List<RepairRequest> getRequestsByUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByUsername(authentication.getName());
        return requestRepository.findByUser(user);
    }

    public List<RepairRequest> getRequestsByMaster(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByUsername(authentication.getName());
        return requestRepository.findByMasters(user);
    }


    public void setRequestComment(String comment, int requestId){
        RepairRequest request = requestRepository.findById(requestId);
        request.setComment(comment);
        requestRepository.save(request);
    }


}
