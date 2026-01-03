package com.example.collegeDb.services;

import com.example.collegeDb.entities.AdmissionRecord;
import com.example.collegeDb.entities.Student;
import com.example.collegeDb.repositories.AdmissionRecordRepository;
import com.example.collegeDb.repositories.ProfessorRepository;
import com.example.collegeDb.repositories.StudentRepository;
import com.example.collegeDb.repositories.SubjectRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class CollegeManagementService {

    private final AdmissionRecordRepository admissionRecordRepository;
    private final ProfessorRepository professorRepository;
    private final StudentRepository studentRepository;
    private final SubjectRepository subjectRepository;


    public CollegeManagementService(AdmissionRecordRepository admissionRecordRepository, ProfessorRepository professorRepository, StudentRepository studentRepository, SubjectRepository subjectRepository) {
        this.admissionRecordRepository = admissionRecordRepository;
        this.professorRepository = professorRepository;
        this.studentRepository = studentRepository;
        this.subjectRepository = subjectRepository;
    }

    @Transactional
    public void addStudentAdmissionRecord(Student student, AdmissionRecord admissionRecord) {
        student.setAdmissionRecord(admissionRecord);
        studentRepository.save(student);
    }

}
