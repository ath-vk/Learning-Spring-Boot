package com.example.collegeDb.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "professors")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder
public class Professor {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String title;

    // inverse side of association between Professor and Subject
    @OneToMany(mappedBy = "professor", cascade = CascadeType.ALL, orphanRemoval = false)
    private List<Subject> subjects = new ArrayList<>();

    // Professor <-> Students (M:N)
    @ManyToMany
    @JoinTable(
            name = "professor_students",
            joinColumns = @JoinColumn(name = "professor_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id")
    )
    private List<Student> students = new ArrayList<>();

}
