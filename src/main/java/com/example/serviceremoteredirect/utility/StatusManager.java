package com.example.serviceremoteredirect.utility;


import com.example.serviceremoteredirect.model.Status;
import com.example.serviceremoteredirect.storage.StatusStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StatusManager {

    @Autowired
    StatusStorage storage;

   public Status getStatusForUser(String username){

       return storage.getStatusForUser(username);
    }

    public void updateStatusForUser(String username, Status status){

       storage.updateStatus(username,status);

    }

}
