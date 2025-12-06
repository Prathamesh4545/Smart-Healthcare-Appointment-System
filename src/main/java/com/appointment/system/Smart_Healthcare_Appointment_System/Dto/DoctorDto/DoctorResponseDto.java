package com.appointment.system.Smart_Healthcare_Appointment_System.Dto.DoctorDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorResponseDto {
    private Long id;

    private Long userId;
    private String email;

    private Long specializationId;
    private String specializationName;

    private String fullName;
    private int experienceYear;
    private boolean approved;
}
