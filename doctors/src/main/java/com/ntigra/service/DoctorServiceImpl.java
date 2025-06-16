package com.ntigra.service;

import com.ntigra.dto.DoctorDTO;
import com.ntigra.entity.Doctor;
import com.ntigra.entity.DoctorAvailability;
import com.ntigra.repo.DoctorAvailabilityRepo;
import com.ntigra.repo.DoctorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DoctorServiceImpl implements DoctorService {

    @Autowired
    private DoctorRepo doctorRepo;

    @Autowired
    private DoctorAvailabilityRepo doctorAvailabilityRepo;
    @Override
    public Doctor addDoctor(DoctorDTO dto) {
        Doctor doc = new Doctor();
        doc.setName(dto.getName());
        doc.setSpecialization(dto.getSpecialization());
        doc.setContactInfo(dto.getContactInfo());

        List<DoctorAvailability> availabilities = dto.getWeeklyAvailability().stream().map(avail -> {
            DoctorAvailability doctorAvailability = new DoctorAvailability();
            doctorAvailability.setDayOfWeek(avail.getDayOfWeek());
            doctorAvailability.setStartTime(avail.getStartTime());
            doctorAvailability.setEndTime(avail.getEndTime());

            doctorAvailability.setDoctor(doc);
            return doctorAvailability;
        }).collect(Collectors.toList());

        doc.setWeeklyAvailability(availabilities);

        Doctor doctor = doctorRepo.save(doc);
        return doctor;
    }

    @Override
    public List<Doctor> getDoctor(String specialization) {
        List<Doctor> doctorList = doctorRepo.findBySpecialization(specialization);
        return doctorList;
    }

    @Override
    public Doctor getDocById(Long id) {
        List<DoctorAvailability> doctorAvailabilityList = new ArrayList<>();
        Doctor doctor = doctorRepo.findById(id).orElseThrow(() -> new RuntimeException("Doctor Id not available"));
        //DoctorAvailability availability = doctorAvailabilityRepo.findById(id).orElseThrow(() -> new RuntimeException("Doctor id not found"));
      //  doctorAvailabilityList = doctorAvailabilityRepo.findByDoctorId(id);
        //doctorAvailabilityList.add(doctorAvailabilityList);
      //  doctor.setWeeklyAvailability(doctorAvailabilityList);
        return doctor;
    }
}
