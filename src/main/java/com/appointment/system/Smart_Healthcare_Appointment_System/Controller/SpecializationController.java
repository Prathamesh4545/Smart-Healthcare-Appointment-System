package com.appointment.system.Smart_Healthcare_Appointment_System.Controller;

import com.appointment.system.Smart_Healthcare_Appointment_System.Dto.SpecializationDto.SpecializationRequestDto;
import com.appointment.system.Smart_Healthcare_Appointment_System.Dto.SpecializationDto.SpecializationResponseDto;
import com.appointment.system.Smart_Healthcare_Appointment_System.Model.Specialization;
import com.appointment.system.Smart_Healthcare_Appointment_System.Service.SpecializationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/specializations")
@RequiredArgsConstructor
public class SpecializationController {

    private final SpecializationService specializationService;

    @PostMapping
    public ResponseEntity<SpecializationResponseDto> createSpecialization(@RequestBody Specialization specialization){
        SpecializationResponseDto response = specializationService.create(specialization);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<SpecializationResponseDto>> getAllSpecialization(){
        return ResponseEntity.ok(specializationService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SpecializationResponseDto> getSpecialization(@PathVariable Long id) {
        return ResponseEntity.ok(specializationService.findSpecializationById(id));
    }

    @GetMapping("/search")
    public ResponseEntity<SpecializationResponseDto> search(@RequestParam String name) {
        return ResponseEntity.ok(specializationService.findSpecializationByName(name));
    }


    @PutMapping("/{id}")
    public ResponseEntity<SpecializationResponseDto> updateSpecialization(@PathVariable Long id,@RequestBody SpecializationRequestDto dto){
        SpecializationResponseDto response = specializationService.update(id,dto);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SpecializationResponseDto> deleteSpecialization(@PathVariable Long id){
        specializationService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
