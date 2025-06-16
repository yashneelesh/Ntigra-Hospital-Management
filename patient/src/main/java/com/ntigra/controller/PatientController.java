package com.ntigra.controller;


import com.ntigra.dto.PatientDTO;
import com.ntigra.dto.UpdatePatientDTO;
import com.ntigra.entity.Patient;
import com.ntigra.service.PatientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/patient")
public class PatientController {

    @Autowired
    private PatientService patientService;
    @PostMapping("/add")
    public ResponseEntity<HashMap<String, Object>> addPatient(@Valid @RequestBody PatientDTO patientDTO){
        HashMap<String, Object> result = new HashMap<>();
        Patient savedPatient = patientService.add(patientDTO);

        result.put("Patient created with Id", savedPatient.getId());
        result.put("message", "Patient added successfully");

        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updatePatient(@PathVariable Long id, @RequestBody UpdatePatientDTO patientDTO){
        patientService.update(patientDTO, id);
        return ResponseEntity.ok("Patient updated successfully");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletePatient (@PathVariable Long id){
        patientService.delete(id);
        return ResponseEntity.ok("Patient with id = "+ id +" deleted successfully");
    }

    @GetMapping("/get/{id}")
    public Patient getPatient (@PathVariable Long id){
       Patient patient =  patientService.getPatient(id);
       return patient;

    }


}
