package com.example.collegeDb.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "subjects")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(nullable = false, length = 100)
    private String title;

    // owning side in association between Subject to Professor
    @ManyToOne
    @JoinColumn(nullable = false, name = "professor_id")
    private Professor professor;

    // Student <-> Subject (M:N) inverse side
    @ManyToMany(mappedBy = "subjects")
    private List<Student> students = new ArrayList<>();

}
