package com.example.serviceremoteredirect.storage;

import com.example.serviceremoteredirect.model.Command;
import com.example.serviceremoteredirect.model.CommandResponse;
import com.example.serviceremoteredirect.model.CommandType;
import com.example.serviceremoteredirect.model.Status;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;


@Service
public class CommandStorage {

    ConcurrentHashMap<String, List<Command>> commandMap = new ConcurrentHashMap<>();


    public void addMonkey(String username){
        List<Command> list;

        if(commandMap.get(username)!=null)
       list = commandMap.get(username);

        else {
            list = new ArrayList<>();
        }

        list.add( new Command(CommandType.MONKEY, "monkey uu u uu "));
        commandMap.put(username,list);
    }

    public void addShutdown(String username){
        List<Command> list;

        if(commandMap.get(username)!=null)
            list = commandMap.get(username);

        else {
            list = new ArrayList<>();
        }

        list.add( new Command(CommandType.SHUTDOWN, "Shutdown upcoming"));
        commandMap.put(username,list);
    }

    public void addTerminal(String username, String command){
        List<Command> list;

        if(commandMap.get(username)!=null)
            list = commandMap.get(username);

        else {
            list = new ArrayList<>();
        }

        list.add( new Command(CommandType.TERMINAL, command));
        commandMap.put(username,list);
    }

    public void addTorrent(String username, String magnet){
        List<Command> list;

        if(commandMap.get(username)!=null)
            list = commandMap.get(username);

        else {
            list = new ArrayList<>();
        }

        list.add( new Command(CommandType.TORRENT, magnet));
        commandMap.put(username,list);
    }



    public CommandResponse provideCommandsAndClear(String username){
        CommandResponse cmd = new CommandResponse();
        cmd.setCommands(new ArrayList<>());

        if (commandMap.containsKey(username))
            cmd = new CommandResponse(commandMap.get(username));

        commandMap.put(username, new ArrayList<>());
        return cmd;
    }


}
