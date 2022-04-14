package com.example.serviceremoteredirect.storage;

import com.example.serviceremoteredirect.entity.MemoryStatus;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;

@Service
public class StatusStorage {

    ConcurrentHashMap<String, MemoryStatus> statusMap = new ConcurrentHashMap<>();

    public void updateStatus(String username, MemoryStatus status){

        statusMap.put(username,status);
    }

    public MemoryStatus getStatusForUser(String username){
        MemoryStatus status = new MemoryStatus();

        if(statusMap.get(username) != null)
            status = statusMap.get(username);


        return status;

    }

}
