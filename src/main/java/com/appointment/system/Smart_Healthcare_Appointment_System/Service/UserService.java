package com.appointment.system.Smart_Healthcare_Appointment_System.Service;

import java.util.List;

import com.appointment.system.Smart_Healthcare_Appointment_System.Dto.UserDto.UserRequestDto;
import com.appointment.system.Smart_Healthcare_Appointment_System.Dto.UserDto.UserResponseDto;

public interface UserService {
    UserResponseDto createUser(UserRequestDto dto);
    UserResponseDto updateUser(Long id, UserRequestDto dto);
    List<UserResponseDto> getAllUsers();
    UserResponseDto findByEmail(String email);
    UserResponseDto deleteUserById(Long id);
    UserResponseDto findUserById(Long id);
}
