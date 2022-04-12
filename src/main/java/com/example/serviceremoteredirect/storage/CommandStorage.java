package com.example.serviceremoteredirect.storage;

import com.example.serviceremoteredirect.model.Command;
import com.example.serviceremoteredirect.model.CommandType;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Data
@NoArgsConstructor
@Component
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

    public void add(String username){
        ArrayList<Command> list = new ArrayList();
        list.add( new Command(CommandType.TERMINAL, "xdg-open www.google.com"));
        commandMap.put(username,list);
    }
}
