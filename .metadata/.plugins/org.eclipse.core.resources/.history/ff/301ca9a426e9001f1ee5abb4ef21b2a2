package com.hospital.service;

import java.util.List;

import com.hospital.dto.HospitalDTO;
import com.hospital.entity.ClaimRequest;
import com.hospital.entity.Hospital;

public interface HospitalService {

	public HospitalDTO registerHospital(Hospital hospital);
	
	public HospitalDTO getHospitalById(Long id);
	
	public HospitalDTO updateHospital(Hospital hospital);
	
	public void deleteHospital(Long id);
	
	public HospitalDTO loginHospital(String email, String password);
	
	public ClaimRequest generateClaimReq(ClaimRequest cr);

	public ClaimRequest updateClaimReq(ClaimRequest cr);
	
	public List<ClaimRequest> getClaimsByHospitalId(long hospitalId);
}
