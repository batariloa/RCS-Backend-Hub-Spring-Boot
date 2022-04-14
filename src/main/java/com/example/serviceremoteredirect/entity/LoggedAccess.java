package com.example.serviceremoteredirect.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class LoggedAccess {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String timestamp;
    private String username;
    @ManyToOne(cascade = CascadeType.PERSIST)
    private MemoryStatus status;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Location location;


    @ManyToOne(cascade = CascadeType.PERSIST)
    private  OperatingSystem operatingSystem;


}
