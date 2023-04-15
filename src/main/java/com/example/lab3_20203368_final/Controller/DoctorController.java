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

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = "/doctores")
public class DoctorController {
   final DoctorRepository doctorRepository;
   final HospitalRepository hospitalRepository;
   final PacienteRepository pacienteRepository;

    public DoctorController(DoctorRepository doctorRepository, HospitalRepository hospitalRepository, PacienteRepository pacienteRepository) {
        this.doctorRepository = doctorRepository;
        this.hospitalRepository = hospitalRepository;
        this.pacienteRepository = pacienteRepository;
    }

    @GetMapping(value = "/lista")
    public String listDoc(Model model){
        model.addAttribute("list",doctorRepository.findAll());
        model.addAttribute("hosp",hospitalRepository);
        return "doctor/listadoc";
    }

    @GetMapping(value = "/pacientes")
    public String pacientePorDoctor(Model model, @RequestParam("id") Integer id){
        Optional<Doctor> doctorOptional = doctorRepository.findById(id);
        if(doctorOptional.isPresent()){
            List<Paciente> pacientes = pacienteRepository.pacientePorDoc(id);
            Doctor doctor = doctorOptional.get();
            model.addAttribute("doc",doctor);
            model.addAttribute("pac",pacientes);
            return"doctor/pacientes";
        }
        else {
            return "redirect:/hospital/lista";
        }
    }

    @GetMapping(value = "/proxCitas")
    public String pacienteProxCita(Model model, @RequestParam("id") Integer id){
        Optional<Doctor> doctorOptional = doctorRepository.findById(id);
        if(doctorOptional.isPresent()){
            List<Paciente> pacientes = pacienteRepository.pacienteProxCita(id);
            Doctor doctor = doctorOptional.get();
            model.addAttribute("doc",doctor);
            model.addAttribute("pac",pacientes);
            return"doctor/pacientesProxcita";
        }
        else {
            return "redirect:/hospital/lista";
        }
    }

}
