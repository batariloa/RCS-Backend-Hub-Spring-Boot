package com.example.serviceremoteredirect.controller;

import com.example.serviceremoteredirect.entity.LoggedAccess;
import com.example.serviceremoteredirect.repository.LoggedAccessRepository;
import com.example.serviceremoteredirect.storage.StatusManager;
import com.example.serviceremoteredirect.storage.StatusStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class StatisticsController {


    @Autowired
    LoggedAccessRepository loggedAccessRepository;

    @Autowired
    StatusManager statusManager;

    @PostMapping("/status")
    String getControlls( @RequestBody LoggedAccess loggedAccess) {


        statusManager.updateStatusForUser(loggedAccess.getUsername(),loggedAccess.getStatus());
        loggedAccessRepository.save(loggedAccess);
        return "Statistics sent.";
    }
}