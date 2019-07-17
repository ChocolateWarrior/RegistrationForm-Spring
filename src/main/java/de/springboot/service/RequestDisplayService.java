package de.springboot.service;

import de.springboot.model.Master;
import de.springboot.model.RepairRequest;
import de.springboot.model.RequestState;
import de.springboot.repository.RequestRepository;
import de.springboot.repository.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Log4j2
@Service
public class RequestDisplayService {

    private final RequestRepository requestRepository;

    @Autowired
    public RequestDisplayService(RequestRepository requestRepository) {
        this.requestRepository = requestRepository;
    }

    public List<RepairRequest> getAllRequests(){
        List <RepairRequest> res = new ArrayList<>();
        requestRepository.findAll().forEach(res::add);
        return res;
    }

    public void removeRequest(RepairRequest request){
        requestRepository.delete(request);
    }

    private RepairRequest getRequestById(int requestId){
        return requestRepository.findById(requestId);
    }

    public void setRequestMaster(int requestId, Master master){
        RepairRequest request = getRequestById(requestId);
        request.setMaster(master.getId());
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

    public void setRequestFinish(int requestId){
        RepairRequest request = getRequestById(requestId);
        request.setFinishTime(LocalDateTime.now());
        request.setState(RequestState.COMPLETED);
        requestRepository.save(request);
    }


}
