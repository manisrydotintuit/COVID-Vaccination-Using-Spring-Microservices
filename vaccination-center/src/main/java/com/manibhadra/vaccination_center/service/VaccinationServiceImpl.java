package com.manibhadra.vaccination_center.service;

import com.manibhadra.vaccination_center.entity.VaccinationCenter;
import com.manibhadra.vaccination_center.model.Citizen;
import com.manibhadra.vaccination_center.model.RequiredResponse;
import com.manibhadra.vaccination_center.repo.VaccinationCenterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VaccinationServiceImpl {

    @Autowired
    VaccinationCenterRepository vaccinationCenterRepository;
    @Autowired
    private RestTemplate restTemplate;

    public VaccinationCenter addVaccinationCenter(VaccinationCenter vaccinationCenter) {
        return vaccinationCenterRepository.save(vaccinationCenter);
    }

    public Optional<VaccinationCenter> getVaccinationCenterById(int id) {
        return vaccinationCenterRepository.findById(id);
    }

    public RequiredResponse getDataForRequiredResponse(Integer id) {
        RequiredResponse requiredResponse = new RequiredResponse();
        Optional<VaccinationCenter> optionalCenter = vaccinationCenterRepository.findById(id);
        if (optionalCenter.isPresent()) {
            VaccinationCenter center = optionalCenter.get();
            requiredResponse.setCenter(center);
            List<Citizen> citizenList = restTemplate.getForObject("http://localhost:8089/citizen/id/" + id, List.class);
            requiredResponse.setCitizenList(citizenList);
        }
        return requiredResponse;
    }

    public RequiredResponse getHandleCitizenDownTime(Integer id) {
        RequiredResponse requiredResponse = new RequiredResponse();
        Optional<VaccinationCenter> optionalCenter = vaccinationCenterRepository.findById(id);
        if (optionalCenter.isPresent()) {
            requiredResponse.setCenter(optionalCenter.get());
            requiredResponse.setCitizenList(new ArrayList<Citizen>());
        }
        return requiredResponse;
    }
}
