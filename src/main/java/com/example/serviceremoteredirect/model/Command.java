package com.example.serviceremoteredirect.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Command {

    private CommandType type;
    private String command;
}
