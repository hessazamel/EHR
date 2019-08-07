package com.zamelh.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
	
	public Patient updatePatient(long patientId, Patient patientDetails) {

			Patient patient = getPatientById(patientId);

			if (patientDetails.getFirstName() != null) {
				patient.setFirstName(patientDetails.getFirstName());
			} else {
				patient.setFirstName(patient.getFirstName());
			}

			if (patientDetails.getSecondName() != null) {
				patient.setSecondName(patientDetails.getSecondName());
			} else {
				patient.setSecondName(patient.getSecondName());
			}

			if (patientDetails.getLastName() != null) {
				patient.setLastName(patientDetails.getLastName());
			} else {
				patient.setLastName(patient.getLastName());
			}

			if (patientDetails.getDob() != null) {
				patient.setDob(patientDetails.getDob());
			} else {
				patient.setDob(patient.getDob());
			}

			if (patientDetails.getHeartRate() != 0) {
				patient.setHeartRate(patientDetails.getHeartRate());
			} else {
				patient.setHeartRate(patient.getHeartRate());
			}

			if (patientDetails.getTemperature() != 0) {
				patient.setTemperature(patientDetails.getTemperature());
			} else {
				patient.setTemperature(patient.getTemperature());
			}

			if (patientDetails.getSystolicBP() != 0) {
				patient.setSystolicBP(patientDetails.getSystolicBP());
			} else {
				patient.setSystolicBP(patient.getSystolicBP());
			}

			if (patientDetails.getDiastolicBP() != 0) {
				patient.setDiastolicBP(patientDetails.getDiastolicBP());
			} else {
				patient.setDiastolicBP(patient.getDiastolicBP());
			}
			
			Patient updated = addPatient(patient);
			return updated;
			
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

	public Page<Patient> getAllPatientsPageable(Pageable pageable) {
		Page<Patient> patientPageable = patientRepository.findAll(pageable);
		return patientPageable;
	}
}
