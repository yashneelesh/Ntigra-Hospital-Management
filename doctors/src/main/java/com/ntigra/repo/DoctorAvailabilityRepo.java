package com.ntigra.repo;

import com.ntigra.entity.DoctorAvailability;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorAvailabilityRepo extends JpaRepository<DoctorAvailability,Long> {
    List<DoctorAvailability> findByDoctorId (Long doctor_id);
}
