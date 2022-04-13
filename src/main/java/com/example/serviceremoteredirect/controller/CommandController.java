package com.example.serviceremoteredirect.controller;

import com.example.serviceremoteredirect.model.CommandResponse;

import com.example.serviceremoteredirect.model.Status;
import com.example.serviceremoteredirect.utility.CommandManager;
import com.example.serviceremoteredirect.utility.StatusManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CommandController {

  @Autowired
  CommandManager commandManager;

  @Autowired
  StatusManager statusManager;

    @PostMapping("/torrent")
    String addTorrent(@RequestParam String username , String magnetLink){


      commandManager.addTorrent(username,magnetLink);

        return "Magnet link added.";
    }

    @PostMapping("/monkey")
    String addMonkey(@RequestParam String username){


        commandManager.addMonkey(username);
        System.out.println("Dodat monkey");

        return "Magnet link added.";
    }


    @PostMapping("/shutdown")
    String shutDown(@RequestParam String username){


        commandManager.addShutdown(username);
        System.out.println("Dodat shutdown");

        return "Magnet link added.";
    }

    @PostMapping("/terminal")
    String terminalCommand(@RequestParam String username, String command){


        commandManager.addTerminal(username,command);
        System.out.println("Added terminal command");

        return "Magnet link added.";
    }


   @PostMapping ("/controls")
   CommandResponse getControlls(@RequestParam String username, @RequestBody Status status){

       System.out.println("username je " + username);
       System.out.println("A status je " + status.getDiskSpaceTotal());


       statusManager.updateStatusForUser(username, status);

       return commandManager.provideCommandsFromStorage(username);
   }



    @GetMapping("/getStatus")
    Status getStatus(@RequestParam String username){
        System.out.println("Pozvan je getStatus");
        System.out.println(statusManager.getStatusForUser(username).toString());
        return statusManager.getStatusForUser(username);
    }




}
