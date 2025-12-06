package com.appointment.system.Smart_Healthcare_Appointment_System.Repository;

import com.appointment.system.Smart_Healthcare_Appointment_System.Model.Specialization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpecializationRepository extends JpaRepository<Specialization, Long> {
    Specialization findByName(String name);
}
