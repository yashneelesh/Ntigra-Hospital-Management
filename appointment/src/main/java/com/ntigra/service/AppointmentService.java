package com.ntigra.service;

import com.ntigra.dto.AppointmentRequestDTO;
import com.ntigra.dto.AppointmentSlotResponse;

import java.time.LocalDate;

public interface AppointmentService {

    AppointmentSlotResponse getAvailableSlots(Long doctorId, LocalDate date);

    String bookAppointment(AppointmentRequestDTO request);
}
