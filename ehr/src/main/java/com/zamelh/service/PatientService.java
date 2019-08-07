package com.zamelh.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zamelh.entity.Patient;
import com.zamelh.repository.PatientRepository;

@Service
public class PatientService {
	
	@Autowired
	PatientRepository patientRepository;

	public Patient addPatient(Patient patient) {
		Patient setPatient= patientRepository.save(patient);
		return setPatient; 
	}
	
	public Patient getPatientById (long patientId) {
		Patient setPatient= patientRepository.findById(patientId).get();
		return setPatient;
	}
	
	public List<Patient> getAllPatients(){
		List<Patient> list = patientRepository.findAll();
		return list;
		
	}
	public void deletePatient(long patientId){
		patientRepository.deleteById(patientId);
	}
}
