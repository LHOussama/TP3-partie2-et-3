package com.example.ensethopital.web;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import com.example.ensethopital.repositories.PatientRepository;
import com.example.ensethopital.entites.Patient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
@Controller
public class PatientController {
    @Autowired
    private PatientRepository patientRepository;
    @GetMapping("/userindex")
    public String index(Model model, @RequestParam(name = "p",defaultValue = "0")
                        int page,@RequestParam(name="s",defaultValue = "4") int size,
                        @RequestParam(name = "keyword",defaultValue ="")String kw){
        Page<Patient> pagePatient=patientRepository.findByNomContaining(kw,PageRequest.of(page,size));
        model.addAttribute("listPatients",pagePatient.getContent());
        model.addAttribute("pages",new int[pagePatient.getTotalPages()]);
        model.addAttribute("currentPage",page);
        model.addAttribute("keyWord",kw);
        return "patients";
    }
    @GetMapping("/admindelete")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String delete(long id,String keyword,int page){
        patientRepository.deleteById(id);
        return "redirect:/userindex?p="+page+"&keyword  ="+keyword;
    }
    @GetMapping("/adminformPatients")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String formPatient(Model model){
        model.addAttribute("patient",new Patient());
        return "formPatients";
    }
    @PostMapping("/adminsave")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String save(Model model,@Valid  Patient patient,BindingResult bindingResult,@RequestParam(name="p",defaultValue = "0") int page,@RequestParam(name="keyword",defaultValue ="" ) String keyword){
        if(bindingResult.hasErrors()) return "formPatients";
        patientRepository.save(patient);
        return "redirect:/userindex?p="+page+"&keyword="+keyword;
    }
    @GetMapping("/admineditPatient")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String editPatient(Model model,long id,int page,String keyword){
        Patient patient=patientRepository.findById(id).orElse(null);
        if(patient==null)
            throw new RuntimeException("Patient Introuvable");
        model.addAttribute("patient",patient);
        model.addAttribute("page",page);
        model.addAttribute("keyword",keyword);
        return "editPatient";
    }
    @GetMapping("/")
    public String home(){
        return "redirect:/userindex";
    }
}
