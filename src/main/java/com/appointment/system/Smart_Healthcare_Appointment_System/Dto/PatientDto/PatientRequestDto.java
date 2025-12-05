package com.appointment.system.Smart_Healthcare_Appointment_System.Dto.PatientDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatientRequestDto {
    private Long id;
    private Long userId;
    private String fullName;
    private String gender;
    private String  contactNumber;
    private LocalDate dateOfBirth;
}
