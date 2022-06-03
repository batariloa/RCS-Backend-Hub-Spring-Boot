package com.example.serviceremoteredirect.controller;

import com.example.serviceremoteredirect.entity.LoggedAccess;
import com.example.serviceremoteredirect.entity.MemoryStatus;
import com.example.serviceremoteredirect.repository.LoggedAccessRepository;
import com.example.serviceremoteredirect.storage.StatusManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class StatisticsController {

    @Autowired
    LoggedAccessRepository loggedAccessRepository;

    @Autowired
    StatusManager statusManager;
    //Save client's access, and save users status locally
    @PostMapping("/status")
    String logAccess( @RequestBody LoggedAccess loggedAccess) {
        statusManager.updateStatusForUser(loggedAccess.getUsername(),loggedAccess.getStatus());
        loggedAccessRepository.save(loggedAccess);
        return "Statistics sent.";
    }
    //Get users latest status
    @GetMapping("/status")
    MemoryStatus getMemoryStatus(@RequestParam String username){
        return statusManager.getStatusForUser(username);
    }
}