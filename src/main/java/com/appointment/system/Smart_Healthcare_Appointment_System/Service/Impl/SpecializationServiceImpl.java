package com.appointment.system.Smart_Healthcare_Appointment_System.Service.Impl;

import com.appointment.system.Smart_Healthcare_Appointment_System.Dto.SpecializationDto.SpecializationRequestDto;
import com.appointment.system.Smart_Healthcare_Appointment_System.Dto.SpecializationDto.SpecializationResponseDto;
import com.appointment.system.Smart_Healthcare_Appointment_System.Exceptions.ResourceNotFoundException;
import com.appointment.system.Smart_Healthcare_Appointment_System.Mapper.SpecializationMapper;
import com.appointment.system.Smart_Healthcare_Appointment_System.Model.Specialization;
import com.appointment.system.Smart_Healthcare_Appointment_System.Repository.SpecializationRepository;
import com.appointment.system.Smart_Healthcare_Appointment_System.Service.SpecializationService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class SpecializationServiceImpl implements SpecializationService {

    private final SpecializationRepository specializationRepository;
    private final SpecializationMapper specializationMapper;

    // ------------ CREATE -------------
    @Override
    public SpecializationResponseDto create(Specialization specialization) {
        Specialization saved = specializationRepository.save(specialization);
        return specializationMapper.toDto(saved);
    }


    // ------------ READ ALL -------------
    @Override
    public List<SpecializationResponseDto> getAll() {
        return specializationRepository.findAll()
                .stream()
                .map(specializationMapper::toDto)
                .collect(Collectors.toList());
    }

    // ------------ READ BY ID -------------
    @Override
    public SpecializationResponseDto findSpecializationById(Long id) {
        Specialization specialization = specializationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Specialization not found with ID: " + id));

        return specializationMapper.toDto(specialization);
    }

    // ------------ READ BY NAME -------------
    @Override
    public SpecializationResponseDto findSpecializationByName(String name) {

        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Specialization name cannot be empty");
        }

        Specialization specialization = specializationRepository.findByName(name);

        if (specialization == null) {
            throw new ResourceNotFoundException("Specialization not found with name: " + name);
        }

        return specializationMapper.toDto(specialization);
    }

    // ------------ UPDATE -------------
    @Override
    public SpecializationResponseDto update(Long id, SpecializationRequestDto dto) {
        Specialization specialization = specializationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Specialization not found with ID: " + id));

        specialization.setName(dto.getName());
        Specialization updated = specializationRepository.save(specialization);

        return specializationMapper.toDto(updated);
    }

    // ------------ DELETE -------------
    @Override
    public SpecializationResponseDto delete(Long id) {
        Specialization specialization = specializationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Specialization not found with ID: " + id));

        SpecializationResponseDto response = specializationMapper.toDto(specialization);
        specializationRepository.delete(specialization);

        return response;
    }
}
