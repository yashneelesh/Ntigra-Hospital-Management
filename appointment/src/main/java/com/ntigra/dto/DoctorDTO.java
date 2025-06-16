package com.ntigra.dto;

import java.util.List;

public class DoctorDTO {

    private Long id;
    private String name;
    private String specialization;
    private String contactInfo;
    private List<WeeklyAvailabilityDTO> weeklyAvailability;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public List<WeeklyAvailabilityDTO> getWeeklyAvailability() {
        return weeklyAvailability;
    }

    public void setWeeklyAvailability(List<WeeklyAvailabilityDTO> weeklyAvailability) {
        this.weeklyAvailability = weeklyAvailability;
    }
}
