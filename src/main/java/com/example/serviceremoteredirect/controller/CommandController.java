package com.example.serviceremoteredirect.controller;

import com.example.serviceremoteredirect.entity.LoggedAccess;
import com.example.serviceremoteredirect.globalVariables.KafkaConstants;
import com.example.serviceremoteredirect.model.Command;
import com.example.serviceremoteredirect.model.CommandResponse;

import com.example.serviceremoteredirect.entity.MemoryStatus;
import com.example.serviceremoteredirect.model.CommandType;
import com.example.serviceremoteredirect.repository.LoggedAccessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
public class CommandController {


  @Autowired
  LoggedAccessRepository loggedAccessRepository;

    // Autowiring Kafka Template
    @Autowired
    KafkaTemplate<String, Command> kafkaTemplate;

    @PostMapping("/terminal")
    String terminalCommand(@RequestParam String username,@RequestParam String command){


        kafkaTemplate.send(
                KafkaConstants.COMMANDS.toString(),
                new Command(username,CommandType.TERMINAL,command));

        System.out.println("Added terminal command for username '"+username+"'");

        return "Terminal command sent.";
    }



    @PostMapping("/torrent")
    String addTorrent(@RequestParam String username , String magnetLink){


        kafkaTemplate.send(
                KafkaConstants.COMMANDS.toString(),
                new Command(username,CommandType.TORRENT,magnetLink));

        return "Magnet link added.";
    }

    @PostMapping("/monkey")
    String addMonkey(@RequestParam String username){



        kafkaTemplate.send(
                KafkaConstants.COMMANDS.toString(),
                new Command(username,CommandType.MONKEY));

        return "Magnet link added.";
    }


    @PostMapping("/shutdown")
    String shutDown(@RequestParam String username){


        kafkaTemplate.send(
                KafkaConstants.COMMANDS.toString(),
                new Command(username,CommandType.TORRENT));

        System.out.println("Sent shutdown command.");

        return "Sent shutdown command.";
    }










}
