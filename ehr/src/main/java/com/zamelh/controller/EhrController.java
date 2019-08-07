package com.zamelh.controller;

import java.util.List;
import java.util.NoSuchElementException;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zamelh.dto.BaseResponseDTO;
import com.zamelh.dto.GetPatientListResponseDTO;
import com.zamelh.dto.GetPatientPageableResponseDTO;
import com.zamelh.dto.GetPatientResponseDTO;
import com.zamelh.entity.Patient;
import com.zamelh.service.PatientService;

@RestController
public class EhrController {

	@Autowired
	PatientService patientService;

	//GetPatientResponseDTO patientResponse = new GetPatientResponseDTO();
	//GetPatientListResponseDTO listResponse = new GetPatientListResponseDTO();

	@RequestMapping("/hello")
	public String hello() {
		return "hello!!";
	}

	/* Add patient */
	@PostMapping(value = { "/patient" })
	// @Valid prevents the exception from being caught, as it already validates the
	// enteries.
	public GetPatientResponseDTO add(@RequestBody Patient patient) {
		GetPatientResponseDTO patientResponse = new GetPatientResponseDTO();
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
	public GetPatientResponseDTO update(@PathVariable(value = "id") Long patientId, @RequestBody Patient patientDetails) {
		GetPatientResponseDTO patientResponse = new GetPatientResponseDTO();
		try {
			Patient updated = patientService.updatePatient(patientId, patientDetails);

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
		GetPatientResponseDTO patientResponse = new GetPatientResponseDTO();
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
		GetPatientListResponseDTO listResponse = new GetPatientListResponseDTO();
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
	
	/* Find all Pagination */
	@GetMapping(value="getAllPageable")
	public GetPatientPageableResponseDTO getAllPatientsPageable(Pageable pageable, Sort sort ) {
		GetPatientPageableResponseDTO pageableResponse = new GetPatientPageableResponseDTO();
		
		Page<Patient> patientPage = patientService.getAllPatientsPageable(pageable);
		if (patientPage.isEmpty()) {
			pageableResponse.setCode(1);
			pageableResponse.setResult("Failure");
			pageableResponse.setMessage("No patients were found");
			pageableResponse.setPatientPageable(null);
		} else {
			pageableResponse.setCode(0);
			pageableResponse.setResult("Success");
			pageableResponse.setMessage("Patients' records retrieved successfully");
			pageableResponse.setPatientPageable(patientPage);
		}
		return pageableResponse;
	}

	/* Delete Patient */
	@DeleteMapping("/patient/{id}")
	public BaseResponseDTO deleteById(@PathVariable(value = "id") Long patientId) {
		BaseResponseDTO response = new BaseResponseDTO();
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
