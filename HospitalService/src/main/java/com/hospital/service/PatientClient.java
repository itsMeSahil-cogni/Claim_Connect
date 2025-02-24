package com.hospital.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.hospital.dto.ApiResponse;
import com.hospital.entity.PatientDTO;

@FeignClient(name = "PATIENTSERVICE")
public interface PatientClient {

	@GetMapping("/api/patient/{id}")
    public ApiResponse<PatientDTO> getPatient(@PathVariable Long id);
}
