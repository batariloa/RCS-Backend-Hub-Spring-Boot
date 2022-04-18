package com.example.serviceremoteredirect.controller;

import com.example.serviceremoteredirect.entity.LoggedAccess;
import com.example.serviceremoteredirect.repository.LoggedAccessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class StatisticsController {


    @Autowired
    LoggedAccessRepository loggedAccessRepository;

    @PostMapping("/status")
    String getControlls( @RequestBody LoggedAccess status) {


        loggedAccessRepository.save(status);
        return "Statistics sent.";
    }
}