package com.example.collegeDb;

import com.example.collegeDb.entities.Student;
import com.example.collegeDb.repositories.AdmissionRecordRepository;
import com.example.collegeDb.repositories.StudentRepository;
import com.example.collegeDb.services.StudentService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Transactional
class CollegeDbApplicationTests {

    @Autowired
    StudentService studentService;

    @Autowired
    AdmissionRecordRepository admissionRecordRepository;

    @Autowired
    StudentRepository studentRepository;

    @Test
    void createStudent_shouldAlsoCreateAdmissionRecord_dueToCascade() {
        Student s = studentService.createStudent("Atharva", 50000);
        assertNotNull(s.getId());
        assertEquals(1, studentRepository.count());
        assertEquals(1, admissionRecordRepository.count());
    }

    @Test
    void unlinkAdmissionRecord_shouldDeleteIt_dueToOrphanRemoval() {
        Student s = studentService.createStudent("Atharva", 50000);
        studentService.removeAdmissionRecord(s.getId());
        assertEquals(1, studentRepository.count());
        assertEquals(0, admissionRecordRepository.count());
    }

}
