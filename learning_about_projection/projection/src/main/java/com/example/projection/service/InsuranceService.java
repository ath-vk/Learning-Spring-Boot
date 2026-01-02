package com.example.projection.service;

import com.example.projection.entity.Insurance;
import com.example.projection.entity.Patient;
import com.example.projection.repository.InsuranceRepository;
import com.example.projection.repository.PatientRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InsuranceService {

    private final InsuranceRepository insuranceRepository;
    private final PatientRepository patientRepository;

    @Transactional
    public Insurance assignInsuranceToPatient(Insurance insurance, Long patientId) {
        Patient patient = patientRepository.findById(patientId).orElseThrow();
        patient.setInsurance(insurance); // dirty patient -> thus synchronize the patient / insurance
        insurance.setPatient(patient); // optional this doesn't change database as patient owns the relationship
        return insurance;
    }

    @Transactional
    public void deletePatient(Long patientId) {
        //patientRepository.findById(patientId).orElseThrow();
        patientRepository.deleteById(patientId);
    }

}
