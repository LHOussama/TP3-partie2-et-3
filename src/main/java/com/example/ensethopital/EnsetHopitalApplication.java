package com.example.ensethopital;

import com.example.ensethopital.entites.Patient;
import com.example.ensethopital.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Pageable;

import java.util.Date;

@SpringBootApplication
public class EnsetHopitalApplication implements CommandLineRunner {
    @Autowired
    private PatientRepository patientRepository;
    public static void main(String[] args) {
        SpringApplication.run(EnsetHopitalApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
       /* Patient patient1=new Patient();
        patient1.setNom("Oussama");
        patient1.setMalade(false);
        patient1.setDateNaissance(new Date());
        patient1.setScore(100);
        Patient patient2=new Patient(null,"yassine",new Date(),true,200);
        Patient patient3=Patient.builder().nom("mehdi").malade(false).dateNaissance(new Date()).score(300).build();
        patientRepository.save(patient1);
        patientRepository.save(patient2);
        patientRepository.save(patient3);*/
    }
}
