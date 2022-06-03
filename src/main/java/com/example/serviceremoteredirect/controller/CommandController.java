package com.example.serviceremoteredirect.controller;

import com.example.serviceremoteredirect.globalVariables.KafkaConstants;
import com.example.serviceremoteredirect.model.Command;
import com.example.serviceremoteredirect.model.CommandType;
import com.example.serviceremoteredirect.model.GenericResponse;
import com.example.serviceremoteredirect.repository.LoggedAccessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommandController {
    @Autowired
    LoggedAccessRepository loggedAccessRepository;
    // Autowiring Kafka Template
    @Autowired
    KafkaTemplate<String, Command> kafkaTemplate;

    @PostMapping("/terminal")
    GenericResponse terminalCommand(@RequestParam String username,@RequestParam String command){
        kafkaTemplate.send(
                KafkaConstants.COMMANDS.toString(),
                new Command(username,CommandType.TERMINAL,command));

        System.out.println("Added terminal command for username '"+username+"'");

        return new GenericResponse("Added terminal command for username'"+username+"'");
    }

    @PostMapping("/torrent")
    GenericResponse addTorrent(@RequestParam String username , String magnetLink){

        System.out.println("Added torrent");
        kafkaTemplate.send(
                KafkaConstants.COMMANDS.toString(),
                new Command(username,CommandType.TORRENT,magnetLink));

        return new GenericResponse("Magnet link added.");
    }

    @PostMapping("/monkey")
    GenericResponse addMonkey(@RequestParam String username){
        System.out.println("IMAGE SHOWN");
        kafkaTemplate.send(
                KafkaConstants.COMMANDS.toString(),
                new Command(username,CommandType.MONKEY));

        return new GenericResponse("Monkey command added");
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
