package com.appointment.system.Smart_Healthcare_Appointment_System.Controller;

import com.appointment.system.Smart_Healthcare_Appointment_System.Dto.DoctorDto.DoctorRequestDto;
import com.appointment.system.Smart_Healthcare_Appointment_System.Dto.DoctorDto.DoctorResponseDto;
import com.appointment.system.Smart_Healthcare_Appointment_System.Service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doctors")
@RequiredArgsConstructor
public class DoctorController {

    private final DoctorService doctorService;

    @PostMapping
    public ResponseEntity<DoctorResponseDto> createDoctor(@RequestBody DoctorRequestDto dto){
        DoctorResponseDto response = doctorService.createDoctor(dto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DoctorResponseDto> updateDoctor(@PathVariable Long id,@RequestBody DoctorRequestDto dto){
        DoctorResponseDto response = doctorService.updateDoctor(id,dto);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<DoctorResponseDto>> getAllDoctors(){
        return ResponseEntity.ok(doctorService.getAllDoctors());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DoctorResponseDto> getDoctorById(@PathVariable Long id){
        DoctorResponseDto response = doctorService.getDoctorById(id);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DoctorResponseDto> deleteDoctorById(@PathVariable Long id){
        doctorService.deleteDoctorById(id);
        return ResponseEntity.noContent().build();
    }

}
