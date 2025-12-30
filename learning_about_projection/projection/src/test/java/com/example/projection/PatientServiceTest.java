package com.example.projection;
import com.example.projection.dto.BloodGroupStats;
import com.example.projection.dto.CPatientInfo;
import com.example.projection.dto.IPatientInfo;
import com.example.projection.entity.Patient;
import com.example.projection.repository.PatientRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


@SpringBootTest
public class PatientServiceTest {

    @Autowired
    private PatientRepository patientRepository;

    @Test
    public void testPatient() {
        //List<Patient> patientList = patientRepository.findAll();
        //List<IPatientInfo> patientList = patientRepository.getPatientInfo();
        //List<CPatientInfo> patientList = patientRepository.getAllPatientInfoConcrete();
        List<BloodGroupStats> bloodGroupStats = patientRepository.getBloodGroupStats();

        for(var patient : bloodGroupStats) {
            System.out.println(patient);
        }

        int rowsAffected = patientRepository.updatePatientNameWithId("Anuj Sharma", 1L);
        System.out.println(rowsAffected);

    }

}
