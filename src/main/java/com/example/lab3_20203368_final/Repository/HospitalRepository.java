package com.example.lab3_20203368_final.Repository;

import com.example.lab3_20203368_final.entity.Doctor;
import com.example.lab3_20203368_final.entity.Hospital;
import com.example.lab3_20203368_final.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface HospitalRepository extends JpaRepository<Hospital,Integer> {

    @Query(nativeQuery = true,value = "select * from hospital where id=?1")
    Hospital hospitalDeDoctor(Integer id);

    @Query(nativeQuery = true,value = "select * from hospital where id=?1")
    Hospital hospitalPaciente(Integer id);
}
