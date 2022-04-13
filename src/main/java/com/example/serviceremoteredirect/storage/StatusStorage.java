package com.example.serviceremoteredirect.storage;

import com.example.serviceremoteredirect.model.Status;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;

@Service
public class StatusStorage {

    ConcurrentHashMap<String, Status> statusMap = new ConcurrentHashMap<>();

    public void updateStatus(String username, Status status){

        statusMap.put(username,status);
    }

    public Status getStatusForUser(String username){
        Status status = new Status();

        if(statusMap.get(username) != null)
            status = statusMap.get(username);


        return status;

    }

}
