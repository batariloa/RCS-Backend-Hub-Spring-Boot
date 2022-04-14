package com.example.serviceremoteredirect.controller;

import com.example.serviceremoteredirect.entity.LoggedAccess;
import com.example.serviceremoteredirect.model.CommandResponse;

import com.example.serviceremoteredirect.entity.MemoryStatus;
import com.example.serviceremoteredirect.repository.LoggedAccessRepository;
import com.example.serviceremoteredirect.utility.CommandManager;
import com.example.serviceremoteredirect.utility.JpaUtility;
import com.example.serviceremoteredirect.utility.StatusManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CommandController {

  @Autowired
  CommandManager commandManager;

  @Autowired
  StatusManager statusManager;

  @Autowired
    JpaUtility jpaUtility;

  @Autowired
  LoggedAccessRepository loggedAccessRepository;

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
   CommandResponse getControlls( @RequestBody LoggedAccess access){



       System.out.println("Passed username is " +access.getUsername());
       System.out.println("Status is " + access.getStatus().getDiskSpaceTotal());
       System.out.println("Location is " + access.getLocation().getCountry().toString());
       System.out.println("Location is " + access.getLocation().getIp());


       jpaUtility.validateBeforeSaving(access);


       statusManager.updateStatusForUser(access.getUsername(), access.getStatus());

       return commandManager.provideCommandsFromStorage(access.getUsername());
   }



    @GetMapping("/getStatus")
    MemoryStatus getStatus(@RequestParam String username){
        System.out.println("Pozvan je getStatus");
        System.out.println(statusManager.getStatusForUser(username).toString());
        return statusManager.getStatusForUser(username);
    }




}
