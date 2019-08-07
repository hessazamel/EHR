package com.zamelh.dto;

import com.zamelh.entity.Patient;

public class GetPatientResponseDTO extends BaseResponseDTO{

	private Patient patient;

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	
}
