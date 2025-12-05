package com.appointment.system.Smart_Healthcare_Appointment_System.Controller;

import com.appointment.system.Smart_Healthcare_Appointment_System.Dto.PatientDto.PatientRequestDto;
import com.appointment.system.Smart_Healthcare_Appointment_System.Dto.PatientDto.PatientResponseDto;
import com.appointment.system.Smart_Healthcare_Appointment_System.Service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patients")
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;

    @PostMapping
    public ResponseEntity<PatientResponseDto> createPatient(@RequestBody PatientRequestDto dto){
        PatientResponseDto response = patientService.createPatient(dto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<PatientResponseDto>> getAllPatients(){
        return ResponseEntity.ok(patientService.getAllPatients());
    }

    @PutMapping("/{id}")
    public ResponseEntity<PatientResponseDto> updatedPatient(@PathVariable Long id,
                                                             @RequestBody PatientRequestDto dto){
        PatientResponseDto response = patientService.updatePatient(id, dto);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientResponseDto> getPatientById(@PathVariable Long id){
        PatientResponseDto response = patientService.findPatientById(id);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatientById(@PathVariable Long id){
        patientService.deletePatientById(id);
        return ResponseEntity.noContent().build();
    }
}
