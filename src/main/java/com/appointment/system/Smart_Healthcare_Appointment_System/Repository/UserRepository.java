package com.appointment.system.Smart_Healthcare_Appointment_System.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.appointment.system.Smart_Healthcare_Appointment_System.Model.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
