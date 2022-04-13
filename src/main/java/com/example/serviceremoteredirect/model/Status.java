package com.example.serviceremoteredirect.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.File;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;


@Data
public class Status {

    private String status;
    private LocalDate date;
    private long diskSpaceUsable;
    private long diskSpaceTotal;

    public Status() {
        this.status = "Status not found yet...";
        this.date = LocalDate.now();
        this.diskSpaceUsable = 0;
        this.diskSpaceTotal =  0;


    }


}
