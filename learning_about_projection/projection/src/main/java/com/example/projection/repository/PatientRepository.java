package com.example.projection.repository;

import com.example.projection.dto.BloodGroupStats;
import com.example.projection.dto.CPatientInfo;
import com.example.projection.dto.IPatientInfo;
import com.example.projection.entity.Patient;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PatientRepository extends JpaRepository<Patient, Long> {

    @Query("select p.id as id, p.name as name, p.email as email from Patient p")
    List<IPatientInfo> getPatientInfo();

    @Query("select new com.example.projection.dto.CPatientInfo(p.id, p.name) "+"from Patient p")
    List<CPatientInfo> getAllPatientInfoConcrete();

    @Query("select new com.example.projection.dto.BloodGroupStats(p.bloodGroup, "+"COUNT(p)) from Patient p group by p.bloodGroup order by COUNT(p)")
    List<BloodGroupStats> getBloodGroupStats();

    @Modifying
    @Transactional
    @Query("UPDATE Patient p set p.name = :name where p.id = :id")
    int updatePatientNameWithId(@Param("name") String name, @Param("id") Long id);
}
