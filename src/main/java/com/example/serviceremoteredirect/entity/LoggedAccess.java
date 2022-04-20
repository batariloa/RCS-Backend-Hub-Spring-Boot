package com.example.serviceremoteredirect.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
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

    public String getUsername() {
        return username;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public MemoryStatus getStatus() {
        return status;
    }

    public Location getLocation() {
        return location;
    }

    public OperatingSystem getOperatingSystem() {
        return operatingSystem;
    }

    public Long getId() {
        return id;
    }
}

