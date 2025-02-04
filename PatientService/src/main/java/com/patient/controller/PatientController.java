package com.patient.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.patient.dto.ApiResponse;
import com.patient.dto.PatientDTO;
import com.patient.entity.ClaimRequest;
import com.patient.entity.Patient;
import com.patient.service.PatientService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/patient")
@AllArgsConstructor
public class PatientController {

    // Service layer dependency for handling patient-related operations
    private PatientService patSer;

    /**
     * Registers a new patient.
     *
     * @param patient The patient entity to be registered.
     * @return ApiResponse containing the registered PatientDTO and a success message.
     */
    @PostMapping("/signup")
    public ApiResponse<PatientDTO> registerPatient(@RequestBody Patient patient) {
        PatientDTO registeredPatient = patSer.registerPatient(patient);
        return new ApiResponse<>(HttpStatus.CREATED, "Patient registered successfully", registeredPatient);
    }

    /**
     * Retrieves a patient by their ID.
     *
     * @param id The unique identifier of the patient.
     * @return ApiResponse containing the PatientDTO and a success message.
     */
    @GetMapping("/{id}")
    public ApiResponse<PatientDTO> getPatient(@PathVariable Long id) {
        PatientDTO patient = patSer.getPatientById(id);
        return new ApiResponse<>(HttpStatus.OK, "Patient retrieved successfully", patient);
    }

    /**
     * Updates an existing patient's information.
     *
     * @param patient The patient entity with updated information.
     * @return ApiResponse containing the updated PatientDTO and a success message.
     */
    @PutMapping("/update")
    public ApiResponse<PatientDTO> updatePatient(@RequestBody Patient patient) {
        PatientDTO updatedPatient = patSer.updatePatient(patient);
        return new ApiResponse<>(HttpStatus.OK, "Patient updated successfully", updatedPatient);
    }

    /**
     * Deletes a patient by their ID.
     *
     * @param id The unique identifier of the patient to be deleted.
     * @return ApiResponse containing a success message.
     */
    @DeleteMapping("/{id}")
    public ApiResponse<String> deletePatient(@PathVariable Long id) {
        patSer.deletePatient(id);
        return new ApiResponse<>(HttpStatus.OK, "Patient deleted successfully", "Success");
    }

    /**
     * Authenticates a patient using email and password.
     *
     * @param email    The patient's email address.
     * @param password The patient's password.
     * @return ApiResponse containing the PatientDTO and a success message.
     */
    @PostMapping("/login")
    public ApiResponse<PatientDTO> loginPatient(@RequestParam String email, @RequestParam String password) {
        PatientDTO patient = patSer.loginPatient(email, password);
        return new ApiResponse<>(HttpStatus.OK, "Login Success", patient);
    }

    /**
     * Retrieves all claims associated with a patient by their ID.
     *
     * @param patientId The unique identifier of the patient.
     * @return ApiResponse containing the list of ClaimRequest objects and a success message.
     */
    @GetMapping("/getClaims/{patientId}")
    public ApiResponse<List<ClaimRequest>> getClaimByPatientId(@PathVariable long patientId) {
        List<ClaimRequest> claimList = patSer.getClaimsByPatientId(patientId);
        return new ApiResponse<>(HttpStatus.OK, "Successful retrieval of claims list", claimList);
    }

    /**
     * Accepts a claim request by its ID.
     *
     * @param id The unique identifier of the claim.
     * @return ApiResponse containing the updated ClaimRequest and a success message.
     */
    @PutMapping("/acceptClaim/{id}")
    public ApiResponse<ClaimRequest> acceptClaim(@PathVariable long id) {
        ClaimRequest claim = patSer.acceptClaim(id);
        return new ApiResponse<>(HttpStatus.OK, "Claim accepted successfully", claim);
    }

    /**
     * Reverts a claim request by its ID with a provided status message.
     *
     * @param id            The unique identifier of the claim.
     * @param statusMessage The message explaining the reason for revert.
     * @return ApiResponse containing the updated ClaimRequest and a success message.
     */
    @PutMapping("/revertClaim/{id}")
    public ApiResponse<ClaimRequest> revertClaim(@PathVariable long id, @RequestBody String statusMessage) {
        ClaimRequest claim = patSer.revertClaim(id, statusMessage);
        return new ApiResponse<>(HttpStatus.OK, "Claim reverted successfully", claim);
    }

    /**
     * Rejects a claim request by its ID with a provided status message.
     *
     * @param id            The unique identifier of the claim.
     * @param statusMessage The message explaining the reason for rejection.
     * @return ApiResponse containing the updated ClaimRequest and a success message.
     */
    @PutMapping("/rejectClaim/{id}")
    public ApiResponse<ClaimRequest> rejectClaim(@PathVariable long id, @RequestBody String statusMessage) {
        ClaimRequest claim = patSer.rejectClaim(id, statusMessage);
        return new ApiResponse<>(HttpStatus.OK, "Claim rejected successfully", claim);
    }

}
