package com.ntigra.service;

import com.ntigra.dto.DoctorDTO;
import com.ntigra.entity.Doctor;

import java.util.List;

public interface DoctorService {
    Doctor addDoctor(DoctorDTO dto);

    List<Doctor> getDoctor(String specialization);

    Doctor getDocById(Long id);
}
