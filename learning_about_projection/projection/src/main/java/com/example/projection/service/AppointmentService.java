package com.example.projection.service;

import com.example.projection.entity.Appointment;
import com.example.projection.entity.Doctor;
import com.example.projection.entity.Patient;
import com.example.projection.repository.AppointmentRepository;
import com.example.projection.repository.DoctorRepository;
import com.example.projection.repository.InsuranceRepository;
import com.example.projection.repository.PatientRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AppointmentService {

    private final PatientRepository patientRepository;
    private final AppointmentRepository appointmentRepository;
    private final DoctorRepository doctorRepository;

    @Transactional
    public Appointment createNewAppointment(Appointment appointment, Long patientId, Long doctorId) {
        Patient patient = patientRepository.findById(patientId).orElseThrow();
        Doctor doctor = doctorRepository.findById(doctorId).orElseThrow();

        appointment.setPatient(patient);
        appointment.setDoctor(doctor);

        appointmentRepository.save(appointment);

        return appointment;
    }

}
