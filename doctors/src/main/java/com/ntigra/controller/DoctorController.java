package com.ntigra.controller;

import com.ntigra.dto.DoctorDTO;
import com.ntigra.entity.Doctor;
import com.ntigra.service.DoctorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/doctor")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @PostMapping("/add")
    public ResponseEntity<HashMap<String, Object>> addDoctor(@Valid @RequestBody DoctorDTO dto){
        HashMap<String, Object> response = new HashMap<>();
        Doctor doctor = doctorService.addDoctor(dto);
        response.put("Doctor with Id", doctor.getId());
        response.put("message","Doctor created Successfully");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/get")
    public List<Doctor> getListOfDoctor(@RequestParam("specialization") String specialization){
        return doctorService.getDoctor(specialization);
    }

    @GetMapping("/{id}")
    public Doctor getDoctor(@PathVariable Long id){
       Doctor doc =  doctorService.getDocById(id);
        return doc;
    }
}
