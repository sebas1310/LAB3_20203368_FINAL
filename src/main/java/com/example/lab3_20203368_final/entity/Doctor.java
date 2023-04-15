package com.example.lab3_20203368_final.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Controller;

@Getter
@Setter
@Entity
@Table(name = "doctor")
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "especialidad", nullable = false)
    private String especialidad;

    @Column(name = "hospital_id", nullable = false)
    private Integer hospitalid;
}
