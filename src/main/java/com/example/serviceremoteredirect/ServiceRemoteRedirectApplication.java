package com.example.serviceremoteredirect;

import com.example.serviceremoteredirect.model.Command;
import com.example.serviceremoteredirect.model.CommandType;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;
import sun.tools.jar.CommandLine;

@SpringBootApplication
public class ServiceRemoteRedirectApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceRemoteRedirectApplication.class, args);
    }


}
