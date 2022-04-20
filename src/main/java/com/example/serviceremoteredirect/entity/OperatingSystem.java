package com.example.serviceremoteredirect.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@AllArgsConstructor
@Entity
@Component
public class OperatingSystem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String architecture;
    private String version;


    public OperatingSystem(){
        this.name = "Unknown";
        this.architecture = "Unknown";
        this.version = "Unknown";
    }



}
