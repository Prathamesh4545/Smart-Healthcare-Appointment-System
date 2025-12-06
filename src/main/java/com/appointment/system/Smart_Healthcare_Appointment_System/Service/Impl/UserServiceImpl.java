package com.appointment.system.Smart_Healthcare_Appointment_System.Service.Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.appointment.system.Smart_Healthcare_Appointment_System.Dto.UserDto.UserRequestDto;
import com.appointment.system.Smart_Healthcare_Appointment_System.Dto.UserDto.UserResponseDto;
import com.appointment.system.Smart_Healthcare_Appointment_System.Exceptions.ResourceNotFoundException;
import com.appointment.system.Smart_Healthcare_Appointment_System.Mapper.UserMapper;
import com.appointment.system.Smart_Healthcare_Appointment_System.Model.User;
import com.appointment.system.Smart_Healthcare_Appointment_System.Model.UserRole;
import com.appointment.system.Smart_Healthcare_Appointment_System.Repository.UserRepository;
import com.appointment.system.Smart_Healthcare_Appointment_System.Service.UserService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    // ----------CREATE-----------
    @Override
    public UserResponseDto createUser(UserRequestDto dto) {
        User entity = userMapper.toEntity(dto);
        entity.setPassword(passwordEncoder.encode(dto.getPassword()));
        User saved = userRepository.save(entity);
        return userMapper.toDto(saved);
    }

    // ----------UPDATE-----------
    @Override
    public UserResponseDto updateUser(Long id, UserRequestDto dto) {
        User existingUser = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found by id "+id));
        existingUser.setEmail(dto.getEmail());
        if(dto.getPassword() != null && !dto.getPassword().isEmpty()){
            existingUser.setPassword(passwordEncoder.encode(dto.getPassword()));
        }
        try {
            existingUser.setRole(UserRole.valueOf(dto.getRole().toUpperCase()));
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid user role: " + dto.getRole());
        }

        User updatedUser = userRepository.save(existingUser);
        return userMapper.toDto(updatedUser);
    }

    // ----------READ-----------
    @Override
    public List<UserResponseDto> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
    }


    @Override
    public UserResponseDto findByEmail(String email){
        User user = userRepository.findByEmail(email);

        if(user == null) {
            throw new ResourceNotFoundException("User not found with email: " + email);
        }

        return userMapper.toDto(user);
    }


    // ----------DELETE-----------
    @Override
    public UserResponseDto deleteUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
        UserResponseDto responseDto = userMapper.toDto(user);
        userRepository.delete(user);
        return responseDto;
    }

    @Override
    public UserResponseDto findUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: "+id));
        return userMapper.toDto(user);
    }

}
