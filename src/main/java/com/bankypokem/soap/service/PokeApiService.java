package com.bankypokem.soap.service;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
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


    public String getPokemon(String name){
        HttpHeaders headers= new HttpHeaders();
        headers.set("User-Agent", "Application");
        HttpEntity<String> entity= new HttpEntity<>("parameters", headers);
        ResponseEntity<String> response = restTemplate.exchange(API_URL + name, HttpMethod.GET, entity, String.class);
        log.info(response.getBody());
        return response.getBody();
    }
}
