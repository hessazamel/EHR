package com.zamelh.dto;

import java.util.List;

import com.zamelh.entity.Patient;

public class GetPatientListResponseDTO extends BaseResponseDTO{
	
private List<Patient> patientList;
	
	public List<Patient> getPatientList() {
		return patientList;
	}

	public void setPatientList(List<Patient> patientList) {
		this.patientList = patientList;
	}

}
