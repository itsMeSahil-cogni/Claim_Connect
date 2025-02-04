package com.insurancecompany.service;

import java.util.List;

import com.insurancecompany.dto.InsuranceCompanyDTO;
import com.insurancecompany.entity.ClaimRequest;
import com.insurancecompany.entity.InsuranceCompany;

public interface InsuranceCompanyService {
	
	public InsuranceCompanyDTO	 registerInsuranceCompany(InsuranceCompany insuranceComapny);

	public InsuranceCompanyDTO getInsuranceCompanyById(Long id);

	public InsuranceCompanyDTO updateInsuranceCompany(InsuranceCompany insuranceComapny);

	public void deleteInsuranceCompany(Long id);

	public InsuranceCompanyDTO loginInsuranceCompany(String email, String password);
	
	public List<ClaimRequest> getClaimsByInsuranceCompId(long id);
	
	public ClaimRequest approveClaim(long id);
	
	public ClaimRequest revertClaim(long id, String statusMessage);
	
	public ClaimRequest rejectClaim(long id, String statusMessage);
	
	
}
