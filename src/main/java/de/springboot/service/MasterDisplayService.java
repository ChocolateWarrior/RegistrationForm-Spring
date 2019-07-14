package de.springboot.service;

import de.springboot.model.Master;
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
}
