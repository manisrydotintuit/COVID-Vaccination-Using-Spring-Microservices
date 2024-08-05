package com.manibhadra.CitizenService.service;

import com.manibhadra.CitizenService.entity.Citizen;
import com.manibhadra.CitizenService.repo.CitizenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CitizenServiceImpl {

    @Autowired
    public CitizenRepository citizenRepository;


    public List<Citizen> getCitizenList(Integer id) {
        return citizenRepository.findByVaccinationCenterId(id);
    }

    public Citizen addCitizen(Citizen citizen) {
        return citizenRepository.save(citizen);
    }
}
