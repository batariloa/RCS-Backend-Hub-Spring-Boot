package com.example.serviceremoteredirect.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.*;


@Entity
public class MemoryStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String status;
    private long diskSpaceUsable;
    private long diskSpaceTotal;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getDiskSpaceUsable() {
        return diskSpaceUsable;
    }

    public void setDiskSpaceUsable(long diskSpaceUsable) {
        this.diskSpaceUsable = diskSpaceUsable;
    }

    public long getDiskSpaceTotal() {
        return diskSpaceTotal;
    }

    public void setDiskSpaceTotal(long diskSpaceTotal) {
        this.diskSpaceTotal = diskSpaceTotal;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MemoryStatus() {
        this.id = 0L;
        this.status = "Status not found yet...";
        this.diskSpaceUsable = 0;
        this.diskSpaceTotal =  0;


    }


}
