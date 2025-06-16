package com.ntigra.service;

import com.ntigra.dto.AppointmentRequestDTO;
import com.ntigra.dto.AppointmentSlotResponse;
import com.ntigra.dto.DoctorDTO;
import com.ntigra.dto.WeeklyAvailabilityDTO;
import com.ntigra.entity.Appointment;
import com.ntigra.repo.AppointmentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AppointmentServiceImpl implements AppointmentService{

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private AppointmentRepo appointmentRepo;

    @Override
    public AppointmentSlotResponse getAvailableSlots(Long doctorId, LocalDate inputDate) {
        if (inputDate.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Date is older than current date");
        }

        DoctorDTO doctor = restTemplate.getForObject(
                "http://localhost:8083/doctor/" + doctorId,
                DoctorDTO.class);

        DayOfWeek dayOfWeek = inputDate.getDayOfWeek();

        Optional<WeeklyAvailabilityDTO> availabilityOpt = doctor.getWeeklyAvailability().stream()
                .filter(av -> av.getDayOfWeek() == dayOfWeek)
                .findFirst();

        if (!availabilityOpt.isPresent()) {
            return new AppointmentSlotResponse(doctor.getName(), doctor.getSpecialization(), inputDate, new ArrayList<>());
        }

        WeeklyAvailabilityDTO availability = availabilityOpt.get();

        LocalTime start = LocalTime.parse(availability.getStartTime());
        LocalTime end = LocalTime.parse(availability.getEndTime());

        List<LocalDateTime> allSlots = new ArrayList<>();
        for (LocalTime time = start; time.isBefore(end); time = time.plusMinutes(30)) {
            allSlots.add(LocalDateTime.of(inputDate, time));
        }

        return new AppointmentSlotResponse(doctor.getName(), doctor.getSpecialization(), inputDate, allSlots);
    }

    @Override
    public String bookAppointment(AppointmentRequestDTO request) {
        DoctorDTO doctor = restTemplate.getForObject(
                "http://localhost:8083/doctor/" + request.getDoctorId(),
                DoctorDTO.class);

        DayOfWeek appointmentDay = request.getAppointmentTime().getDayOfWeek();

        WeeklyAvailabilityDTO availability = doctor.getWeeklyAvailability().stream()
                .filter(av -> av.getDayOfWeek() == appointmentDay)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Doctor is not available on this day"));

        LocalTime requestedTime = request.getAppointmentTime().toLocalTime();
        LocalTime start = LocalTime.parse(availability.getStartTime());
        LocalTime end = LocalTime.parse(availability.getEndTime());

        if (requestedTime.isBefore(start) || requestedTime.isAfter(end)) {
            throw new IllegalArgumentException("Requested time is outside of doctor's available hours");
        }

        boolean isAlreadyBooked = appointmentRepo
                .findByDoctorIdAndAppointmentTime(request.getDoctorId(), request.getAppointmentTime())
                .isPresent();

        if (isAlreadyBooked) {
            throw new IllegalArgumentException("This slot is already booked");
        }

        boolean alreadyHasAppointment = appointmentRepo
                .findByDoctorIdAndPatientId(request.getDoctorId(), request.getPatientId())
                .isPresent();

        if (alreadyHasAppointment) {
            throw new IllegalArgumentException("Patient already has an appointment with this doctor.");
        }

        Appointment appointment = new Appointment(
                request.getAppointmentTime(), request.getDoctorId(), request.getPatientId(), true);

        appointmentRepo.save(appointment);

        return "Appointment successfully booked.";

    }
}
