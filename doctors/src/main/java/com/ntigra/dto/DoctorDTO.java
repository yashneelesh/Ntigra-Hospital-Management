package com.ntigra.dto;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class DoctorDTO {

    @NotBlank(message = "Name is mandatory")
    private String name;
    @NotBlank(message = "Specialization is mandatory")
    private String specialization;
    @NotBlank(message = "Contact Details is mandatory")
    private String contactInfo;

    private List<WeeklyAvailabilityDTO> weeklyAvailability;

    public List<WeeklyAvailabilityDTO> getWeeklyAvailability() {
        return weeklyAvailability;
    }

    public void setWeeklyAvailability(List<WeeklyAvailabilityDTO> weeklyAvailability) {
        this.weeklyAvailability = weeklyAvailability;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }


}
