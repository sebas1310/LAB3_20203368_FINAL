package com.example.lab3_20203368_final.Repository;

import com.example.lab3_20203368_final.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PacienteRepository extends JpaRepository<Paciente, Integer> {
    @Query(nativeQuery = true,value = "select * from paciente where hospital_id=?1")
    List<Paciente> pacientePorHospital(Integer id);

    @Query(nativeQuery = true,value = "select * from paciente where doctor_id=?1")
    List<Paciente> pacientePorDoc(Integer id);

    @Query(nativeQuery = true,value = "select * from paciente where doctor_id=?1 and fecha_cita > CURDATE()")
    List<Paciente> pacienteProxCita(Integer id);

    @Modifying
    @Query(nativeQuery = true, value = "UPDATE paciente SET numero_habitacion =?1 WHERE id=?2")
    void updateRoom(Integer room, Integer id);

    @Modifying
    @Query(nativeQuery = true, value = "UPDATE paciente SET doctor_id =?1 WHERE doctor_id=?2")
    void derivateDoc(Integer idDoc2, Integer idDoc1);
}
