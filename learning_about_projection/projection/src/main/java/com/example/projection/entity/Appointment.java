package com.example.projection.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime appointmentTime;

    @Column(length = 500)
    private String reason;

    @ManyToOne // owning side
    @JoinColumn(nullable = false, unique = true)
    @ToString.Exclude
    @JsonIgnore
    private Patient patient;

    @ManyToOne // owning side
    @JoinColumn(nullable = false)
    @ToString.Exclude
    @JsonIgnore
    private Doctor doctor;

}
