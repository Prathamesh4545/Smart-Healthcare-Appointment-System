package com.appointment.system.Smart_Healthcare_Appointment_System.Mapper;

import com.appointment.system.Smart_Healthcare_Appointment_System.Dto.PatientDto.PatientRequestDto;
import com.appointment.system.Smart_Healthcare_Appointment_System.Dto.PatientDto.PatientResponseDto;
import com.appointment.system.Smart_Healthcare_Appointment_System.Model.Gender;
import com.appointment.system.Smart_Healthcare_Appointment_System.Model.Patient;
import com.appointment.system.Smart_Healthcare_Appointment_System.Model.User;
import org.springframework.stereotype.Component;

@Component
public class PatientMapper {

    public Patient toEntity(PatientRequestDto dto, User user){
        Patient patient = new Patient();
        patient.setUser(user);
        patient.setFullName(dto.getFullName());
        patient.setContactNumber(dto.getContactNumber());
        patient.setDateOfBirth(dto.getDateOfBirth());

        try {
            patient.setGender(Gender.valueOf(dto.getGender().toUpperCase()));
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid gender: " + dto.getGender());
        }

        return patient;
    }

    public PatientResponseDto toDto(Patient patient){
        PatientResponseDto dto = new PatientResponseDto();
        dto.setId(patient.getId());
        dto.setUserId(patient.getUser().getId());
        dto.setFullName(patient.getFullName());
        dto.setContactNumber(patient.getContactNumber());
        dto.setDateOfBirth(patient.getDateOfBirth());
        dto.setGender(patient.getGender().name());

        return dto;
    }
}
