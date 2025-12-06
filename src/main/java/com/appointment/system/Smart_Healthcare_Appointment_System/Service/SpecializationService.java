package com.appointment.system.Smart_Healthcare_Appointment_System.Service;

import com.appointment.system.Smart_Healthcare_Appointment_System.Dto.SpecializationDto.SpecializationRequestDto;
import com.appointment.system.Smart_Healthcare_Appointment_System.Dto.SpecializationDto.SpecializationResponseDto;
import com.appointment.system.Smart_Healthcare_Appointment_System.Model.Specialization;

import java.util.List;

public interface SpecializationService {
    SpecializationResponseDto create(Specialization specialization);
    List<SpecializationResponseDto> getAll();
    SpecializationResponseDto update(Long id,SpecializationRequestDto dto);
    SpecializationResponseDto delete(Long id);
    SpecializationResponseDto findSpecializationById(Long id);
    SpecializationResponseDto findSpecializationByName(String name);
}
