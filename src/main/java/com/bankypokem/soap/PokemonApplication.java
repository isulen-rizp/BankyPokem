package com.bankypokem.soap;

import com.bankypokem.soap.service.impl.PokemonServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.xml.ws.Endpoint;

@SpringBootApplication
public class PokemonApplication {
    public static void main(String[] args) {
        SpringApplication.run(PokemonApplication.class, args);
        Endpoint.publish("http://localhost:8081/ws/pokemon", new PokemonServiceImpl());
    }
}
