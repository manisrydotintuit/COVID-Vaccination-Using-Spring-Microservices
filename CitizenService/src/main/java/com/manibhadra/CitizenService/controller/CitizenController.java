package com.manibhadra.CitizenService.controller;


import com.manibhadra.CitizenService.entity.Citizen;
import com.manibhadra.CitizenService.service.CitizenServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/citizen")
public class CitizenController {

    @Autowired
    private CitizenServiceImpl citizenService;

    @RequestMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("This is a test response from Citizen Service");
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<List<Citizen>> getById(@PathVariable("id") Integer id) {
        List<Citizen> citizenList = citizenService.getCitizenList(id);
        if (citizenList != null) {
            return ResponseEntity.ok(citizenList);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/addCitizen")
    public ResponseEntity<Citizen> addCitizen(@RequestBody Citizen citizen) {
        Citizen citizen1 = citizenService.addCitizen(citizen);
        if (citizen1 != null) {
            return ResponseEntity.ok(citizen1);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}
