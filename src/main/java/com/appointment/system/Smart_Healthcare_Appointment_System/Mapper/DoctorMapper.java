package com.appointment.system.Smart_Healthcare_Appointment_System.Mapper;

import com.appointment.system.Smart_Healthcare_Appointment_System.Dto.DoctorDto.DoctorRequestDto;
import com.appointment.system.Smart_Healthcare_Appointment_System.Dto.DoctorDto.DoctorResponseDto;
import com.appointment.system.Smart_Healthcare_Appointment_System.Model.Doctor;
import com.appointment.system.Smart_Healthcare_Appointment_System.Model.Specialization;
import com.appointment.system.Smart_Healthcare_Appointment_System.Model.User;
import org.springframework.stereotype.Component;

@Component
public class DoctorMapper {

    public Doctor toEntity(DoctorRequestDto dto, User user, Specialization specialization) {
        if (dto == null) return null;

        Doctor doctor = new Doctor();
        doctor.setUser(user);
        doctor.setFullName(dto.getFullName());
        doctor.setExperienceYear(dto.getExperienceYear());
        doctor.setSpecialization(specialization);
        doctor.setApproved(dto.isApproved());

        return doctor;
    }

    public DoctorResponseDto toDto(Doctor doctor) {
        if (doctor == null) return null;

        DoctorResponseDto dto = new DoctorResponseDto();
        dto.setId(doctor.getId());
        dto.setFullName(doctor.getFullName());
        dto.setExperienceYear(doctor.getExperienceYear());
        dto.setApproved(doctor.isApproved());

        if (doctor.getUser() != null) {
            dto.setUserId(doctor.getUser().getId());
            dto.setEmail(doctor.getUser().getEmail());
        }

        if (doctor.getSpecialization() != null) {
            dto.setSpecializationId(doctor.getSpecialization().getId());
            dto.setSpecializationName(doctor.getSpecialization().getName());
        }

        return dto;
    }
}
