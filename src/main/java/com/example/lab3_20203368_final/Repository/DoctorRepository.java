package com.example.lab3_20203368_final.Repository;

import com.example.lab3_20203368_final.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.print.Doc;
import java.util.List;

public interface DoctorRepository extends JpaRepository<Doctor,Integer> {

    @Query(nativeQuery = true,value = "select * from doctor where hospital_id=?1")
    List<Doctor> doctorPorHospital(Integer id);


    @Query(nativeQuery = true,value = "select * from doctor where id=?1")
    Doctor doctorPorPaciente(Integer id);
}
