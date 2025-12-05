package com.appointment.system.Smart_Healthcare_Appointment_System.Service.Impl;

import com.appointment.system.Smart_Healthcare_Appointment_System.Dto.PatientDto.PatientRequestDto;
import com.appointment.system.Smart_Healthcare_Appointment_System.Dto.PatientDto.PatientResponseDto;
import com.appointment.system.Smart_Healthcare_Appointment_System.Exceptions.ResourceNotFoundException;
import com.appointment.system.Smart_Healthcare_Appointment_System.Mapper.PatientMapper;
import com.appointment.system.Smart_Healthcare_Appointment_System.Model.Gender;
import com.appointment.system.Smart_Healthcare_Appointment_System.Model.Patient;
import com.appointment.system.Smart_Healthcare_Appointment_System.Model.User;
import com.appointment.system.Smart_Healthcare_Appointment_System.Repository.PatientRepository;
import com.appointment.system.Smart_Healthcare_Appointment_System.Repository.UserRepository;
import com.appointment.system.Smart_Healthcare_Appointment_System.Service.PatientService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;
    private final UserRepository userRepository;
    private final PatientMapper patientMapper;

    // ----------HELPER-----------
    private User findUserById(Long id){
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    // ----------CREATE-----------
    @Override
    public PatientResponseDto createPatient(PatientRequestDto dto) {
        User user = findUserById(dto.getUserId());
        Patient patient = patientMapper.toEntity(dto, user);
        Patient saved = patientRepository.save(patient);

        return patientMapper.toDto(saved);
    }

    // ----------UPDATE-----------
    @Override
    public PatientResponseDto updatePatient(Long id, PatientRequestDto dto) {
        Patient existingPatient = patientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found with id: "+id));

        existingPatient.setUser(findUserById(dto.getUserId()));
        existingPatient.setFullName(dto.getFullName());
        existingPatient.setContactNumber(dto.getContactNumber());
        existingPatient.setDateOfBirth(dto.getDateOfBirth());

        try {
            existingPatient.setGender(Gender.valueOf(dto.getGender().trim().toUpperCase()));
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid gender: " + dto.getGender());
        }

        Patient updated = patientRepository.save(existingPatient);
        return patientMapper.toDto(updated);
    }

    // ----------READ-----------
    @Override
    public List<PatientResponseDto> getAllPatients() {
        return patientRepository.findAll()
                .stream()
                .map(patientMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public PatientResponseDto findPatientById(Long id) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found with id: "+id));

        return patientMapper.toDto(patient);
    }

    // ----------DELETE-----------
    @Override
    public PatientResponseDto deletePatientById(Long id) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found with id: "+id));
        PatientResponseDto responseDto = patientMapper.toDto(patient);
        patientRepository.delete(patient);
        return responseDto;
    }
}
