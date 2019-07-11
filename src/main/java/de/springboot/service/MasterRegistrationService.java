package de.springboot.service;

import de.springboot.dto.MasterRegistrationDTO;
import de.springboot.model.Master;
import de.springboot.repository.MasterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MasterRegistrationService {

    private final MasterRepository masterRepository;

    @Autowired
    MasterRegistrationService(MasterRepository masterRepository){
        this.masterRepository=masterRepository;
    }

    public void pushMaster(MasterRegistrationDTO dto){
        Master masterToAdd = Master.builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .login(dto.getLogin())
                .password(dto.getPassword())
                .specifications(dto.getSpecifications())
                .build();
        masterRepository.save(masterToAdd);
    }
}
