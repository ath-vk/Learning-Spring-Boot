package com.example.collegeDb.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "admission_records")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder
public class AdmissionRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(nullable = false)
    private Integer fees;

    // owning side (has the fk column: admission_records.student_id)
    @OneToOne(optional = false)
    @JoinColumn(nullable = false, unique = true, name="student_id")
    private Student student;


}
