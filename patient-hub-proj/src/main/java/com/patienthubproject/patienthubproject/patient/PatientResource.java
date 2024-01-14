package com.patienthubproject.patienthubproject.patient;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;
import java.util.Optional;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.patienthubproject.patienthubproject.jpa.PatientRepository;

import jakarta.persistence.Cacheable;
import jakarta.validation.Valid;

@RestController
public class PatientResource {
	
	private PatientRepository repository;
	
	public PatientResource(PatientRepository repository) {
		this.repository = repository;
	}
	//Retrieve details of patients
	@GetMapping("/patients")
	public List<Patient> retrieveAllPatients(){
		return repository.findAll();
		
	}
	
	@GetMapping("/patients/{id}")
	public EntityModel<Patient> retrievePatient(@PathVariable int id){
		EntityModel<Patient> entityModel = retrievePatientById(id);
		return entityModel;
	}
	
	//Cache Enabled on this patientId
	@org.springframework.cache.annotation.Cacheable("patient")
	public EntityModel<Patient> retrievePatientById(int id)
	{
		Optional<Patient> patient = repository.findById(id);
		 
		 if(patient.isEmpty())
			 throw new UserNotFoundException("id:"+id);
		 
		 EntityModel<Patient> entityModel = EntityModel.of(patient.get());
		 
		 WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveAllPatients());
		 entityModel.add(link.withRel("all-patients"));
		 return entityModel;
	}
	
	
	@DeleteMapping("/patients/{id}")
	public void deletePatient(@PathVariable int id){
		 repository.deleteById(id);
	}
	
	//Add a patient and send its URI
	@PostMapping("/patients")
	public ResponseEntity<Object> createPatient(@Valid @RequestBody Patient patient) {
		Patient savedPatient = repository.save(patient);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(savedPatient.getId())
				.toUri();
		
		return ResponseEntity.created(location).build();
		
	}
	
	

}
