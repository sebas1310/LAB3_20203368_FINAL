package com.example.lab3_20203368_final.Controller;

import com.example.lab3_20203368_final.Repository.DoctorRepository;
import com.example.lab3_20203368_final.Repository.HospitalRepository;
import com.example.lab3_20203368_final.Repository.PacienteRepository;
import com.example.lab3_20203368_final.entity.Doctor;
import com.example.lab3_20203368_final.entity.Hospital;
import com.example.lab3_20203368_final.entity.Paciente;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = "/hospital")
public class HospitalController {
    final HospitalRepository hospitalRepository;
    final DoctorRepository doctorRepository;
    final PacienteRepository pacienteRepository;

    public HospitalController(HospitalRepository hospitalRepository, DoctorRepository doctorRepository, PacienteRepository pacienteRepository) {
        this.hospitalRepository = hospitalRepository;
        this.doctorRepository = doctorRepository;
        this.pacienteRepository = pacienteRepository;
    }


    @GetMapping(value = "/lista")
    public String listHospital(Model model){
        model.addAttribute("list",hospitalRepository.findAll());
        return "hospital/listahospital";
    }

    @GetMapping(value = "/doctores")
    public String doctores(Model model, @RequestParam(name = "id") Integer id){
        Optional<Hospital> optionalHospital = hospitalRepository.findById(id);
        if(optionalHospital.isPresent()){
            Hospital hospital = optionalHospital.get();
            model.addAttribute("hospital",hospital);
            model.addAttribute("doc",doctorRepository.doctorPorHospital(id));
            return"hospital/doctor";
        }
        else {
            return "redirect:/hospital/lista";
        }

    }


    @GetMapping(value = "/pacientes")
    public String paciente(Model model, @RequestParam(name = "id") Integer id){
        Optional<Hospital> hospitalOptional = hospitalRepository.findById(id);
        if(hospitalOptional.isPresent()){
            List<Paciente> pacientes = pacienteRepository.pacientePorHospital(id);
            Hospital hospital = hospitalOptional.get();
            model.addAttribute("hospital",hospital);
            model.addAttribute("pac",pacientes);
            model.addAttribute("doc",doctorRepository);
            return"hospital/paciente";
        }
        else {
            return "redirect:/hospital/lista";
        }

    }
}
