package com.ntigra.service;

import com.ntigra.dto.PatientDTO;
import com.ntigra.dto.UpdatePatientDTO;
import com.ntigra.entity.Patient;
import com.ntigra.repo.PatientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientServiceImpl implements PatientService{

    @Autowired
    private PatientRepo patientRepo;

    @Override
    public Patient add(PatientDTO patientDTO) {
        Patient patient = new Patient();
        patient.setName(patientDTO.getName());
        patient.setAge(patientDTO.getAge());
        patient.setGender(patientDTO.getGender());
        patient.setDateOfBirth(patientDTO.getDateOfBirth());
        patient.setContactInfo(patientDTO.getContactInfo());
        Patient savedPatient = patientRepo.save(patient);
        return savedPatient;
    }

    @Override
    public void update(UpdatePatientDTO dto, Long id) {
        Patient patient = patientRepo.findById(id).orElseThrow(() -> new RuntimeException("patient not found"));
        if(dto.getName() != null){
            patient.setName(dto.getName());
        }
        if(dto.getAge()!= null){
            patient.setAge(dto.getAge());
        }
        if (dto.getGender() != null){
            patient.setGender(dto.getGender());
        }
        if(dto.getDateOfBirth() != null){
            patient.setDateOfBirth(dto.getDateOfBirth());
        }
        if(dto.getContactInfo() != null){
            patient.setContactInfo(dto.getContactInfo());
        }

        patientRepo.save(patient);
    }

    @Override
    public void delete(Long id) {
        patientRepo.deleteById(id);
    }

    @Override
    public Patient getPatient(Long id) {
        Patient patient = patientRepo.getById(id);
        return patient;
    }
}
