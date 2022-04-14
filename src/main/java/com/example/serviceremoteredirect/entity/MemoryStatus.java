package com.example.serviceremoteredirect.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.*;


@Entity

@Data
public class MemoryStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String status;
    private long diskSpaceUsable;
    private long diskSpaceTotal;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MemoryStatus() {
        this.status = "Status not found yet...";
        this.diskSpaceUsable = 0;
        this.diskSpaceTotal =  0;


    }


}
