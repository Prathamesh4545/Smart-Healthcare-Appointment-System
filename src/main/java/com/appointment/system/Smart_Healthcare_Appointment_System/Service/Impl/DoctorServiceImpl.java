package com.appointment.system.Smart_Healthcare_Appointment_System.Service.Impl;

import com.appointment.system.Smart_Healthcare_Appointment_System.Dto.DoctorDto.DoctorRequestDto;
import com.appointment.system.Smart_Healthcare_Appointment_System.Dto.DoctorDto.DoctorResponseDto;
import com.appointment.system.Smart_Healthcare_Appointment_System.Exceptions.ResourceNotFoundException;
import com.appointment.system.Smart_Healthcare_Appointment_System.Mapper.DoctorMapper;
import com.appointment.system.Smart_Healthcare_Appointment_System.Model.Doctor;
import com.appointment.system.Smart_Healthcare_Appointment_System.Model.Specialization;
import com.appointment.system.Smart_Healthcare_Appointment_System.Model.User;
import com.appointment.system.Smart_Healthcare_Appointment_System.Repository.DoctorRepository;
import com.appointment.system.Smart_Healthcare_Appointment_System.Repository.SpecializationRepository;
import com.appointment.system.Smart_Healthcare_Appointment_System.Repository.UserRepository;
import com.appointment.system.Smart_Healthcare_Appointment_System.Service.DoctorService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository doctorRepository;
    private final UserRepository userRepository;
    private final SpecializationRepository specializationRepository;
    private final DoctorMapper doctorMapper;

    // ----------HELPER-----------
    private User findUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
    }

    private Specialization findSpecializationById(Long id) {
        return specializationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Specialization not found with id: " + id));
    }

    private Doctor findDoctorById(Long id) {
        return doctorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not found with id: " + id));
    }


    // ----------CREATE-----------
    @Override
    public DoctorResponseDto createDoctor(DoctorRequestDto dto) {
        User user = findUserById(dto.getUserId());
        Specialization specialization = findSpecializationById(dto.getSpecializationId());
        Doctor entity = doctorMapper.toEntity(dto, user, specialization);
        Doctor saved = doctorRepository.save(entity);
        return doctorMapper.toDto(saved);
    }

    // ----------READ-----------
    @Override
    public List<DoctorResponseDto> getAllDoctors() {
        return doctorRepository.findAll()
                .stream()
                .map(doctorMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public DoctorResponseDto getDoctorById(Long id) {
        Doctor doctor = findDoctorById(id);
        return doctorMapper.toDto(doctor);
    }

    // ----------UPDATE-----------
    @Override
    public DoctorResponseDto updateDoctor(Long id, DoctorRequestDto dto) {
        Doctor existingDoctor = findDoctorById(id);

        existingDoctor.setUser(findUserById(dto.getUserId()));
        existingDoctor.setSpecialization(findSpecializationById(dto.getSpecializationId()));

        existingDoctor.setFullName(dto.getFullName());
        existingDoctor.setExperienceYear(dto.getExperienceYear());
        existingDoctor.setApproved(dto.isApproved());

        Doctor updated = doctorRepository.save(existingDoctor);
        return doctorMapper.toDto(updated);
    }

    // ----------DELETE-----------
    @Override
    public DoctorResponseDto deleteDoctorById(Long id) {
        Doctor doctor = findDoctorById(id);
        DoctorResponseDto response = doctorMapper.toDto(doctor);
        doctorRepository.delete(doctor);
        return response;
    }

}
