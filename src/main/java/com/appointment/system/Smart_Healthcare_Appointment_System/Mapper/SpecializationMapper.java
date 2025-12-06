package com.appointment.system.Smart_Healthcare_Appointment_System.Mapper;

import com.appointment.system.Smart_Healthcare_Appointment_System.Dto.SpecializationDto.SpecializationRequestDto;
import com.appointment.system.Smart_Healthcare_Appointment_System.Dto.SpecializationDto.SpecializationResponseDto;
import com.appointment.system.Smart_Healthcare_Appointment_System.Model.Specialization;
import org.springframework.stereotype.Component;

@Component
public class SpecializationMapper {

    public Specialization toEntity(SpecializationRequestDto dto){
        Specialization specialization = new Specialization();
        specialization.setName(dto.getName());
        return specialization;
    }

    public SpecializationResponseDto toDto(Specialization specialization){
        SpecializationResponseDto response = new SpecializationResponseDto();
        response.setId(specialization.getId());
        response.setName(specialization.getName());
        return response;
    }

}
