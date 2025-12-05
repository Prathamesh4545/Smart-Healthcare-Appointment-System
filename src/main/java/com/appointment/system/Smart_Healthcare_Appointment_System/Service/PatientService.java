package com.appointment.system.Smart_Healthcare_Appointment_System.Service;

import com.appointment.system.Smart_Healthcare_Appointment_System.Dto.PatientDto.PatientRequestDto;
import com.appointment.system.Smart_Healthcare_Appointment_System.Dto.PatientDto.PatientResponseDto;

import java.util.List;

public interface PatientService {
    PatientResponseDto createPatient(PatientRequestDto dto);
    PatientResponseDto updatePatient(Long id,PatientRequestDto dto);
    List<PatientResponseDto> getAllPatients();
    PatientResponseDto findPatientById(Long id);
    PatientResponseDto deletePatientById(Long id);

//    List<PatientResponseDto> getPatientsByDoctorId(Long id);
}
