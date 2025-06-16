package com.ntigra.service;

import com.ntigra.dto.PatientDTO;
import com.ntigra.dto.UpdatePatientDTO;
import com.ntigra.entity.Patient;

public interface PatientService {
    public Patient add(PatientDTO patientDTO);

    void update(UpdatePatientDTO patientDTO, Long id);

    void delete(Long id);

    Patient getPatient(Long id);
}
