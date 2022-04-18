package com.example.serviceremoteredirect.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Command implements Serializable {

    private String username;
    private CommandType type;
    private String command;

    public Command(String username, CommandType type){
        this.username = username;
        this.type = type;
    }
}
