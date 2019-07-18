package de.springboot.service;

import de.springboot.model.Master;
import de.springboot.model.RepairRequest;
import de.springboot.repository.MasterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MasterDisplayService {

    private final MasterRepository masterRepository;

    @Autowired
    public MasterDisplayService(MasterRepository masterRepository) {
        this.masterRepository = masterRepository;
    }

    public List<Master> getAllMasters(){
        List <Master> res = new ArrayList<>();
        masterRepository.findAll().forEach(res::add);
        return res;
    }

    public Master getMasterById(int masterId){
        return masterRepository.findById(masterId);
    }

    public void removeMaster(int masterId){
        Master master = masterRepository.findById(masterId);
        masterRepository.delete(master);
    }
    public void setMasterRequest(Master master, RepairRequest requestToSet){
        master.setRequest(requestToSet);
        masterRepository.save(master);
    }

    public void setMasterFirstName(int masterId, String firstname){
        Master master = masterRepository.findById(masterId);
        master.setFirstName(firstname);
        masterRepository.save(master);
    }

    public void setMasterLastName(int masterId, String lastname){
        Master master = masterRepository.findById(masterId);
        master.setLastName(lastname);
        masterRepository.save(master);
    }

    public void setMasterLogin(int masterId, String login){
        Master master = masterRepository.findById(masterId);
        master.setLogin(login);
        masterRepository.save(master);
    }

    public void setMasterPassword(int masterId, String password){
        Master master = masterRepository.findById(masterId);
        master.setPassword(password);
        masterRepository.save(master);
    }

}
