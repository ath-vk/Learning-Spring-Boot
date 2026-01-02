package com.example.projection;


import com.example.projection.entity.Appointment;
import com.example.projection.entity.Insurance;
import com.example.projection.service.AppointmentService;
import com.example.projection.service.InsuranceService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;

@SpringBootTest
public class InsuranceTests {

    @Autowired
    private InsuranceService insuranceService;

    @Autowired
    private AppointmentService appointmentService;

    @Test
    public void testAssignInsuranceToPatient() {
        Insurance insurance = Insurance.builder()
                        .provider("HDFC Ergo")
                                .policyNumber(("HDFC_236"))
                                        .validUntil(LocalDate.of(2030, 11, 11))
                                                .build();
        var updatedInsurance = insuranceService.assignInsuranceToPatient(insurance, 1L);

        System.out.println(updatedInsurance);

        insuranceService.deletePatient(1L);
    }

    @Test
    public void testCreateAppointment() {
        Appointment appointment = Appointment.builder()
                .appointmentTime(LocalDateTime.of(2025, 11, 1, 14, 0, 12))
                .reason("Headache")
                .build();

        var updatedAppointment = appointmentService.createNewAppointment(appointment, 1L, 2L);

        System.out.println(updatedAppointment);
    }


}
