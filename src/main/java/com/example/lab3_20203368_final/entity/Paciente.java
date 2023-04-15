package com.example.lab3_20203368_final.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "paciente")
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "edad", nullable = false)
    private Integer edad;

    @Column(name = "genero", nullable = false)
    private String genero;

    @Column(name = "diagnostico", nullable = false)
    private String diagnostico;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fecha_cita;

    @Column(name = "numero_habitacion", nullable = false)
    private Integer numerohabitacion;

    @Column(name = "doctor_id",nullable = false)
    private Integer doctorid;

    @Column(name = "hospital_id",nullable = false)
    private Integer hospitalid;
}
