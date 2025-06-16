package com.ntigra.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class AppointmentSlotResponse {

    private String doctorName;
    private String specialization;
    private LocalDate date;
    private List<LocalDateTime> availableSlots;

    public AppointmentSlotResponse() {
    }

    public AppointmentSlotResponse(String doctorName, String specialization, LocalDate date, List<LocalDateTime> availableSlots) {
        this.doctorName = doctorName;
        this.specialization = specialization;
        this.date = date;
        this.availableSlots = availableSlots;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public List<LocalDateTime> getAvailableSlots() {
        return availableSlots;
    }

    public void setAvailableSlots(List<LocalDateTime> availableSlots) {
        this.availableSlots = availableSlots;
    }
}
