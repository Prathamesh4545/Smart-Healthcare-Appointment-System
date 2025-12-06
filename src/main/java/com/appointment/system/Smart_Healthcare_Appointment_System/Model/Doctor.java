package com.appointment.system.Smart_Healthcare_Appointment_System.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "doctors")
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", unique = true)
    private User user;

    private String fullName;

    private int experienceYear;

    @ManyToOne
    @JoinColumn(name = "specialization_id")
    private Specialization specialization;

    private boolean approved;
}
