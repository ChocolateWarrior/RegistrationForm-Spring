package de.springboot.service;

import de.springboot.dto.MasterRegistrationDTO;
import de.springboot.model.Master;
import de.springboot.repository.MasterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MasterRegistrationService
        implements UserDetailsService
{

    private final MasterRepository masterRepository;

    @Autowired
    MasterRegistrationService(MasterRepository masterRepository){
        this.masterRepository=masterRepository;
    }

    public void pushMaster(MasterRegistrationDTO dto){
        Master masterToAdd = Master.builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .username(dto.getLogin())
                .password(dto.getPassword())
                .specifications(dto.getSpecifications())
                .build();
        masterRepository.save(masterToAdd);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Master master = masterRepository.findByUsername(s);

        if (master == null) {
            throw new UsernameNotFoundException(s);
        }

        return master;
    }
}
