package com.appointment.system.Smart_Healthcare_Appointment_System.Mapper;

import org.springframework.stereotype.Component;

import com.appointment.system.Smart_Healthcare_Appointment_System.Dto.UserDto.UserRequestDto;
import com.appointment.system.Smart_Healthcare_Appointment_System.Dto.UserDto.UserResponseDto;
import com.appointment.system.Smart_Healthcare_Appointment_System.Model.User;
import com.appointment.system.Smart_Healthcare_Appointment_System.Model.UserRole;

@Component
public class UserMapper {
    public User toEntity(UserRequestDto dto){
        User user = new User();
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setRole(UserRole.valueOf(dto.getRole()));

        return user;
    }

    public UserResponseDto toDto(User user){
        return new UserResponseDto(
                user.getEmail(),
                user.getRole().name()
        );
    }
}
