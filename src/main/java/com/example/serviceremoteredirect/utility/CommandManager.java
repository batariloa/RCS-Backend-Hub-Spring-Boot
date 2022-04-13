package com.example.serviceremoteredirect.utility;

import com.example.serviceremoteredirect.model.CommandResponse;
import com.example.serviceremoteredirect.storage.CommandStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CommandManager {

    @Autowired
    CommandStorage storage;

    public void addTerminal(String username, String command){
        storage.addTerminal(username,command);

    }

    public void addTorrent(String username, String magnet){
        storage.addTorrent(username,magnet);

    }


    public void addShutdown(String username){
        storage.addShutdown(username);

    }

    public void addMonkey(String username){
        storage.addMonkey(username);

    }




    public CommandResponse provideCommandsFromStorage(String username) {


        return storage.provideCommandsAndClear(username);


    }

}
