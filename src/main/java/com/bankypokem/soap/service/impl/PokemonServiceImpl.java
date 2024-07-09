package com.bankypokem.soap.service.impl;

import com.bankypokem.soap.SpringContext;
import com.bankypokem.soap.service.PokeApiService;
import com.bankypokem.soap.service.PokemonService;
import com.bankypokem.soap.util.Constants;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import javax.jws.HandlerChain;
import javax.jws.WebService;

@WebService(endpointInterface = "com.bankypokem.soap.service.PokemonService")
@HandlerChain(file="handlers.xml")
@Log4j2
@Service
public class PokemonServiceImpl implements PokemonService {

    private PokeApiService pokeApiService;

    public PokemonServiceImpl(){
        pokeApiService= SpringContext.getBean(PokeApiService.class);
    }

    public String abilities(String name) {
        ResponseEntity<String> response= pokeApiService.getPokemon(name);
        String abilitiesResponse= null;
        ObjectMapper mapper= new ObjectMapper();

        try {
            if (response.getStatusCode()== HttpStatus.OK){
                JsonNode rootNode= mapper.readTree(response.getBody());
                JsonNode jobNode= rootNode.get(Constants.ABILITIES);
                abilitiesResponse= mapper.writeValueAsString(jobNode);
            }
            else{
                abilitiesResponse= "Error al obtener abilites pokemon("+ name +"): " + response.getStatusCode() + " - " + response.getBody();
            }

        } catch (JsonProcessingException e) {
            abilitiesResponse= "Error abilites at processing pokemon ("+ name + "): " + e.getMessage();
            e.printStackTrace();
        }

        return abilitiesResponse;
    }

    public String baseExperience(String name) {
        ResponseEntity<String> response= pokeApiService.getPokemon(name);
        String result= null;
        ObjectMapper mapper= new ObjectMapper();

        try {
            if (response.getStatusCode()== HttpStatus.OK) {
                JsonNode rootNode = mapper.readTree(response.getBody());
                JsonNode jobNode= rootNode.get(Constants.BASE_EXPERIENCE);
                result= mapper.writeValueAsString(jobNode);
            }
            else{
                result= "Error al obtener baseExperience pokemon("+ name +"): " + response.getStatusCode() + " - " + response.getBody();
            }

        } catch (JsonProcessingException e) {
            result= "Error baseExperience at processing pokemon ("+ name + "): " + e.getMessage();
            e.printStackTrace();
        }
        return result;
    }

    public String heldItems(String name) {
        ResponseEntity<String> response= pokeApiService.getPokemon(name);
        String result= null;
        ObjectMapper mapper= new ObjectMapper();

        try {
            if (response.getStatusCode()== HttpStatus.OK) {
                JsonNode rootNode = mapper.readTree(response.getBody());
                JsonNode jobNode= rootNode.get(Constants.HELD_ITEMS);
                result= mapper.writeValueAsString(jobNode);
            }
            else{
                result= "Error al obtener heldItems pokemon("+ name +"): " + response.getStatusCode() + " - " + response.getBody();
            }

        } catch (JsonProcessingException e) {
            result= "Error heldItems at processing pokemon ("+ name + "): " + e.getMessage();
            e.printStackTrace();
        }
        return result;
    }

    public String id(String name) {
        ResponseEntity<String> response= pokeApiService.getPokemon(name);
        ObjectMapper mapper= new ObjectMapper();
        String result= null;

        try {
            if (response.getStatusCode()== HttpStatus.OK) {
                JsonNode rootNode = mapper.readTree(response.getBody());
                JsonNode jobNode= rootNode.get(Constants.ID);
                result= mapper.writeValueAsString(jobNode);
            }
            else{
                result= "Error al obtener ID pokemon("+ name +"): " + response.getStatusCode() + " - " + response.getBody();
            }

        } catch (JsonProcessingException e) {
            result= "Error ID at processing pokemon ("+ name + "): " + e.getMessage();
            e.printStackTrace();
        }
        return result;
    }

    public String name(String name) {
        ResponseEntity<String> response= pokeApiService.getPokemon(name);
        ObjectMapper mapper= new ObjectMapper();
        String result= null;

        try {
            if (response.getStatusCode()== HttpStatus.OK) {
                JsonNode rootNode = mapper.readTree(response.getBody());
                JsonNode jobNode= rootNode.get(Constants.NAME);
                result= mapper.writeValueAsString(jobNode);
            }
            else{
                result= "Error al obtener name pokemon("+ name +"): " + response.getStatusCode() + " - " + response.getBody();
            }
        } catch (JsonProcessingException e) {
            result= "Error name at processing pokemon ("+ name + "): " + e.getMessage();
            e.printStackTrace();
        }
        return result;
    }

    public String locationAreaEncounters(String name) {
        ResponseEntity<String> response= pokeApiService.getPokemon(name);
        String result= null;
        ObjectMapper mapper= new ObjectMapper();

        try {
            if (response.getStatusCode()== HttpStatus.OK) {
                JsonNode rootNode = mapper.readTree(response.getBody());
                JsonNode jobNode= rootNode.get(Constants.LOCATION_AREA_ENCOUNTERS);
                result= mapper.writeValueAsString(jobNode);
            }
            else{
                result= "Error al obtener locationAreaEncounters pokemon("+ name +"): " + response.getStatusCode() + " - " + response.getBody();
            }
        } catch (JsonProcessingException e) {
            result= "Error locationAreaEncounters at processing pokemon ("+ name + "): " + e.getMessage();
            e.printStackTrace();
        }
        return result;
    }
}
