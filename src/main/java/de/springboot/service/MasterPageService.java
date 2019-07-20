package de.springboot.service;

import de.springboot.model.Master;
import de.springboot.model.RepairRequest;
import de.springboot.repository.MasterRepository;
import de.springboot.repository.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


@Service
public class MasterPageService {

    private final RequestRepository requestRepository;
    private final MasterRepository masterRepository;

    @Autowired
    public MasterPageService(RequestRepository requestRepository, MasterRepository masterRepository) {
        this.requestRepository = requestRepository;
        this.masterRepository = masterRepository;
    }

    public RepairRequest getRequestByMaster(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Master master = masterRepository.findByUsername(authentication.getName());
        return requestRepository.findByMaster(master);
    }

}
