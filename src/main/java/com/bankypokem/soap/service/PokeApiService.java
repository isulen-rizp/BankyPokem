package com.bankypokem.soap.service;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
@Log4j2
@Getter@Setter
public class PokeApiService {

    private static final String API_URL = "https://pokeapi.co/api/v2/pokemon/";

    private RestTemplate restTemplate;

    PokeApiService(){
        restTemplate= new RestTemplate();
    }

    public ResponseEntity<String> getPokemon(String name){
        if(name==null || name.trim().isEmpty()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Name is empty or null.");
        }
        if(name.trim().equals("?")){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Especial character ? not valid.");
        }
        HttpHeaders headers= new HttpHeaders();
        headers.set("User-Agent", "Application");
        ResponseEntity<String> response = null;

        try{
            HttpEntity<String> entity= new HttpEntity<>("parameters", headers);
            response = restTemplate.exchange(API_URL + name, HttpMethod.GET, entity, String.class);
            log.info(response.getBody());
        }
        catch (HttpClientErrorException ex){
            return ResponseEntity.status(ex.getStatusCode()).body(ex.getStatusText());
        }
        return response;
    }
}