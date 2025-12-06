package com.appointment.system.Smart_Healthcare_Appointment_System.Service;

import com.appointment.system.Smart_Healthcare_Appointment_System.Dto.DoctorDto.DoctorRequestDto;
import com.appointment.system.Smart_Healthcare_Appointment_System.Dto.DoctorDto.DoctorResponseDto;

import java.util.List;

public interface DoctorService {
    DoctorResponseDto createDoctor(DoctorRequestDto dto);
    DoctorResponseDto updateDoctor(Long id,DoctorRequestDto dto);
    List<DoctorResponseDto> getAllDoctors();
    DoctorResponseDto deleteDoctorById(Long id);
    DoctorResponseDto getDoctorById(Long id);
}
