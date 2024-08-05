package com.manibhadra.vaccination_center.controller;


import com.manibhadra.vaccination_center.entity.VaccinationCenter;
import com.manibhadra.vaccination_center.model.RequiredResponse;
import com.manibhadra.vaccination_center.service.VaccinationServiceImpl;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/vaccinationCenter")
public class VaccinationCenterController {

    @Autowired
    private VaccinationServiceImpl vaccinationService;

    @RequestMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("This is a test response from Citizen Service");
    }

    @PostMapping(path = "/addVaccinationCenter")
    public ResponseEntity<VaccinationCenter> addVaccinationCenter(@RequestBody VaccinationCenter vaccinationCenter) {
        VaccinationCenter vaccinationCenterAdd = vaccinationService.addVaccinationCenter(vaccinationCenter);
        if (vaccinationCenterAdd != null) {
            return ResponseEntity.ok(vaccinationCenter);
        } else return ResponseEntity.badRequest().build();
    }

    @GetMapping(path = "/getVaccinationCenter/{id}")
    public ResponseEntity<VaccinationCenter> getVaccinationCenter(@PathVariable("id") int id) {
        Optional<VaccinationCenter> vaccinationCenter = vaccinationService.getVaccinationCenterById(id);
        return vaccinationCenter.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping(path = "/get/{id}")
    @CircuitBreaker(name = "vaccinationCenter", fallbackMethod = "handleCitizenDownTime")
    public ResponseEntity<RequiredResponse> getAllDataBasedOnCenterId(@PathVariable("id") Integer id) {
        RequiredResponse requiredResponse = vaccinationService.getDataForRequiredResponse(id);
        return new ResponseEntity<>(requiredResponse, HttpStatus.OK);
    }

    public ResponseEntity<RequiredResponse> handleCitizenDownTime(Integer id, Throwable throwable) {
        RequiredResponse requiredResponse = vaccinationService.getHandleCitizenDownTime(id);
        return new ResponseEntity<>(requiredResponse, HttpStatus.OK);

    }
}