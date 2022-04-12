package com.example.serviceremoteredirect.controller;

import com.example.serviceremoteredirect.model.Command;
import com.example.serviceremoteredirect.model.CommandResponse;
import com.example.serviceremoteredirect.model.CommandType;
import com.example.serviceremoteredirect.storage.CommandStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
public class CommandController {

    @Autowired
    CommandStorage storage;


    @PostMapping("/torrent")
    String addTorrent(@RequestParam String magnetLink, String username){


        storage.getCommandMap().get("username").add(new Command(CommandType.TORRENT, magnetLink));

        return "Magnet link added.";
    }

    @PostMapping("/monkey")
    String addMonkey(@RequestParam String username){


        storage.addMonkey(username);
        System.out.println("Dodat monkey");

        return "Magnet link added.";
    }

    @GetMapping("/add")
    void add(){

        storage.add("1");


    }

   @GetMapping("/controls")
   CommandResponse getControlls(@RequestParam String username){

       System.out.println("username je " + username);



       CommandResponse cmd = new CommandResponse();
       cmd.setCommands(new ArrayList<>());
       if(storage.getCommandMap().containsKey(username))
       cmd = new CommandResponse(storage.getCommandMap().get(username));
       storage.getCommandMap().put(username, new ArrayList<>());
       return cmd;
   }


}
