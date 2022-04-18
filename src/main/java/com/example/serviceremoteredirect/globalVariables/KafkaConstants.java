package com.example.serviceremoteredirect.globalVariables;

public enum KafkaConstants {

    COMMANDS("commands"), CSUS("");


    private final String text;



    KafkaConstants(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return this.text;
    }
}
