package com.example.collegeDb.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "students")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    // inverse side in association between AdmissionRecord and Student
    @OneToOne(mappedBy = "student", cascade = CascadeType.ALL,  orphanRemoval = true)
    private AdmissionRecord admissionRecord;

    // Student <-> Professors (M:N) inverse side
    @ManyToMany(mappedBy = "students")
    private List<Professor> professors = new ArrayList<>();

    // Students <-> Subjects (M:N)
    @ManyToMany
    @JoinTable(
            name = "student_subjects",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "subject_id")
    )
    private List<Subject> subjects = new ArrayList<>();

}
