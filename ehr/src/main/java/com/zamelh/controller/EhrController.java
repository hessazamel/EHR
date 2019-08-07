package com.zamelh.controller;

import java.util.List;
import java.util.NoSuchElementException;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zamelh.dto.BaseResponseDTO;
import com.zamelh.dto.GetPatientListResponseDTO;
import com.zamelh.dto.GetPatientResponseDTO;
import com.zamelh.entity.Patient;
import com.zamelh.service.PatientService;

@RestController
public class EhrController {

	@Autowired
	PatientService patientService;

	BaseResponseDTO response = new BaseResponseDTO();
	GetPatientResponseDTO patientResponse = new GetPatientResponseDTO();
	GetPatientListResponseDTO listResponse = new GetPatientListResponseDTO();

	@RequestMapping("/hello")
	public String hello() {
		return "hello!!";
	}

	/* Add patient */
	@PostMapping(value = { "/patient" })
	// @Valid prevents the exception from being caught, as it already validates the
	// enteries.
	public GetPatientResponseDTO add(@RequestBody Patient patient) {
		try {
			Patient newPatient = patientService.addPatient(patient);

			patientResponse.setCode(0);
			patientResponse.setResult("Success");
			patientResponse.setMessage("Patient record added successfully");
			patientResponse.setPatient(newPatient);
		} catch (IllegalArgumentException patientNull) {
			patientResponse.setCode(1);
			patientResponse.setResult("Failure");
			patientResponse.setMessage("Patient record can not be empty");
			patientResponse.setPatient(null);
		}
		return patientResponse;
	}

	/* Edit patient */
	@PutMapping("/patient/{id}")
	// @Valid prevents the exception from being caught, as it already validates the
	// enteries.
	public GetPatientResponseDTO update(@PathVariable(value = "id") Long patientId,
			@RequestBody Patient patientDetails) {
		try {
			Patient patient = patientService.getPatientById(patientId);

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

			Patient updated = patientService.addPatient(patient);
			// patientService.addPatient(patient);

			patientResponse.setCode(0);
			patientResponse.setResult("Success");
			patientResponse.setMessage("Patient record updated successfully");
			patientResponse.setPatient(updated);
		} catch (IllegalArgumentException notFound) {
			patientResponse.setCode(1);
			patientResponse.setResult("Failure");
			patientResponse.setMessage("No patient record exists for the given ID");
			patientResponse.setPatient(null);
		}
		// returns a response entity with the given body, header, and status
		return patientResponse;
	} // update

	/* Find By ID */
	@RequestMapping(value = "/patient/{id}")
	public GetPatientResponseDTO getPatientById(@PathVariable(value = "id") Long patientId) {
		try {
			Patient patient = patientService.getPatientById(patientId);

			patientResponse.setCode(0);
			patientResponse.setResult("Success");
			patientResponse.setMessage("Patient record retrieved successfully");
			patientResponse.setPatient(patient);
		} catch (IllegalArgumentException | NoSuchElementException ex) {
			patientResponse.setCode(1);
			patientResponse.setResult("Failure");
			patientResponse.setMessage("No patient record exists for the given ID");
			patientResponse.setPatient(null);
		}
		return patientResponse;
	} // findById

	/* Find all */
	@GetMapping(value = "/getAll")
	public GetPatientListResponseDTO getAllPatients() {
		List<Patient> list = patientService.getAllPatients();
		if (list.size() >= 1) {
			listResponse.setCode(0);
			listResponse.setResult("Success");
			listResponse.setMessage("Patients' records retrieved successfully");
			listResponse.setPatientList(list);
		} else {
			listResponse.setCode(1);
			listResponse.setResult("Failure");
			listResponse.setMessage("No patients were found");
			listResponse.setPatientList(null);
		}
		return listResponse;
	} // findAll

	/* Delete Patient */
	@DeleteMapping("/patient/{id}")
	public BaseResponseDTO deleteById(@PathVariable(value = "id") Long patientId) {
		try {
			patientService.deletePatient(patientId);

			response.setCode(0);
			response.setResult("Success");
			response.setMessage("Patient record deleted successfully");
		} catch (IllegalArgumentException notFound) {
			response.setCode(1);
			response.setResult("Failure");
			response.setMessage("No patient record exists for the given ID");
		}
		return response;
	} // deleteById

}
