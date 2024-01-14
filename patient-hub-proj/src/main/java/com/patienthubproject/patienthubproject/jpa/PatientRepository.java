package com.patienthubproject.patienthubproject.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.patienthubproject.patienthubproject.patient.Patient;

public interface PatientRepository extends JpaRepository<Patient, Integer> {

}
