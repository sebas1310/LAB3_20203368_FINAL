package com.example.lab3_20203368_final.Controller;

import com.example.lab3_20203368_final.Repository.DoctorRepository;
import com.example.lab3_20203368_final.Repository.HospitalRepository;
import com.example.lab3_20203368_final.Repository.PacienteRepository;
import com.example.lab3_20203368_final.entity.Doctor;
import com.example.lab3_20203368_final.entity.Paciente;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = "/paciente")
public class PacienteController {
    final PacienteRepository pacienteRepository;
    final DoctorRepository doctorRepository;
    final HospitalRepository hospitalRepository;

    public PacienteController(PacienteRepository pacienteRepository, DoctorRepository doctorRepository, HospitalRepository hospitalRepository) {
        this.pacienteRepository = pacienteRepository;
        this.doctorRepository = doctorRepository;
        this.hospitalRepository = hospitalRepository;
    }


    @GetMapping(value = "/lista")
    public String pacLista(Model model){
        model.addAttribute("pac",pacienteRepository.findAll());
        model.addAttribute("doc",doctorRepository);
        model.addAttribute("hosp",hospitalRepository);
        return "paciente/listapac";
    }

    @GetMapping(value = "/editpaciente")
    public String editPaciente(Model model, @RequestParam("id") Integer id){
            Optional<Paciente> optionalPaciente = pacienteRepository.findById(id);
            if (optionalPaciente.isPresent()) {
                Paciente paciente = optionalPaciente.get();
                model.addAttribute("pac", paciente);
                model.addAttribute("doc",doctorRepository);
                model.addAttribute("hosp",hospitalRepository);
                return "paciente/editPaciente";
            } else {
                return "redirect:/paciente/lista";
            }
    }

    @PostMapping(value = "/changeRoom")
    @Transactional
    public String editHabitacion(@RequestParam("id") Integer id, @RequestParam("numerohabitacion") Integer room){
        System.out.println("GAAA");
        pacienteRepository.updateRoom(room,id);
        return "redirect:/paciente/lista";
    }

    @GetMapping(value = "/derivar")
    public String derivar(Model model){
        model.addAttribute("doc",doctorRepository.findAll());
        return"paciente/derivarPac";
    }

    @PostMapping(value = "/derivate")
    @Transactional
    public String derivarPac(@RequestParam("doctor1") Integer idDoc1, @RequestParam("doctor2") Integer idDoc2){
        pacienteRepository.derivateDoc(idDoc2,idDoc1);
        return"redirect:/paciente/lista";
    }
}
