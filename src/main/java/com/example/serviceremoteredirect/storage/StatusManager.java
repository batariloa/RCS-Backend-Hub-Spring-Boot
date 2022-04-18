package com.example.serviceremoteredirect.storage;


import com.example.serviceremoteredirect.entity.MemoryStatus;
import com.example.serviceremoteredirect.storage.StatusStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StatusManager {

    @Autowired
    StatusStorage storage;

    public MemoryStatus getStatusForUser(String username){

        return storage.getStatusForUser(username);
    }

    public void updateStatusForUser(String username, MemoryStatus status){

        storage.updateStatus(username,status);

    }

}