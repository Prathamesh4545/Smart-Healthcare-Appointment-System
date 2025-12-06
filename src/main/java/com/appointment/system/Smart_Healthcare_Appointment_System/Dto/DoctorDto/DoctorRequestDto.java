package com.appointment.system.Smart_Healthcare_Appointment_System.Dto.DoctorDto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorRequestDto {
    private Long userId;
    private Long specializationId;
    private String fullName;
    private int experienceYear;
    private boolean approved;
}
