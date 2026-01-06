package com.example.collegeDb.services;

import com.example.collegeDb.entities.AdmissionRecord;
import com.example.collegeDb.entities.Student;
import com.example.collegeDb.repositories.AdmissionRecordRepository;
import com.example.collegeDb.repositories.StudentRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class StudentService {

    private final StudentRepository studentRepository;
    private final AdmissionRecordRepository admissionRecordRepository;


    public StudentService(StudentRepository studentRepository, AdmissionRecordRepository admissionRecordRepository) {
        this.studentRepository = studentRepository;
        this.admissionRecordRepository = admissionRecordRepository;
    }

    public Student createStudent(String name, Integer fees) {
        AdmissionRecord admissionRecord = AdmissionRecord.builder().fees(fees).build();
        Student student = Student.builder().name(name).build();
        student.setAdmissionRecord(admissionRecord);
        return studentRepository.save(student);
    }

    public void removeAdmissionRecord(Long studentId) {
        Student student = studentRepository.findById(studentId).orElse(null);
        if(student != null) {
            student.setAdmissionRecord(null); // orphanRemoval = true -> deletes the row in admission_records
        }
    }

    public void removeStudent(Long studentId) {
        Student student = studentRepository.findById(studentId).orElse(null);
        if(student != null) {
            studentRepository.deleteById(studentId);
        }
    }

}
