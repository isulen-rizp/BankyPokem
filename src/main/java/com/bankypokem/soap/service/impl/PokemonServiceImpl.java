package com.bankypokem.soap.service.impl;

import com.bankypokem.soap.SpringContext;
import com.bankypokem.soap.repository.RequestDataRepository;
import com.bankypokem.soap.service.PokeApiService;
import com.bankypokem.soap.service.PokemonService;
import com.bankypokem.soap.util.Constants;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import javax.jws.HandlerChain;
import javax.jws.WebService;

@WebService(endpointInterface = "com.bankypokem.soap.service.PokemonService")
@HandlerChain(file="handlers.xml")
@Log4j2
public class PokemonServiceImpl implements PokemonService {

    private PokeApiService pokeApiService;
    private RequestDataRepository requestDataRepository;

    public PokemonServiceImpl(){
        pokeApiService= SpringContext.getBean(PokeApiService.class);
        requestDataRepository= SpringContext.getBean(RequestDataRepository.class);
    }

    public String abilities(String name) {
        String response= pokeApiService.getPokemon(name);
        String abilitiesResponse= null;
        ObjectMapper mapper= new ObjectMapper();

        try {
            JsonNode rootNode= mapper.readTree(response);
            JsonNode jobNode= rootNode.get(Constants.ABILITIES);
            abilitiesResponse= mapper.writeValueAsString(jobNode);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return abilitiesResponse;
    }

    public int baseExperience(String name) {
        String response= pokeApiService.getPokemon(name);
        int baseExperience= 0;
        ObjectMapper mapper= new ObjectMapper();

        try {
            JsonNode rootNode= mapper.readTree(response);
            JsonNode jobNode= rootNode.get(Constants.BASE_EXPERIENCE);
            baseExperience= Integer.parseInt(mapper.writeValueAsString(jobNode));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return baseExperience;
    }

    public String heldItems(String name) {
        String response= pokeApiService.getPokemon(name);
        String heldItems= null;
        ObjectMapper mapper= new ObjectMapper();

        try {
            JsonNode rootNode= mapper.readTree(response);
            JsonNode jobNode= rootNode.get(Constants.HELD_ITEMS);
            heldItems= mapper.writeValueAsString(jobNode);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return heldItems;
    }

    public int id(String name) {
        String response= pokeApiService.getPokemon(name);
        int id= 0;
        ObjectMapper mapper= new ObjectMapper();

        try {
            JsonNode rootNode= mapper.readTree(response);
            JsonNode jobNode= rootNode.get(Constants.ID);
            id= Integer.parseInt(mapper.writeValueAsString(jobNode));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return id;
    }

    public String name(String name) {
        String response= pokeApiService.getPokemon(name);
        String pokName= null;
        ObjectMapper mapper= new ObjectMapper();

        try {
            JsonNode rootNode= mapper.readTree(response);
            JsonNode jobNode= rootNode.get(Constants.NAME);
            pokName= mapper.writeValueAsString(jobNode);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return pokName;
    }

    public String locationAreaEncounters(String name) {
        String response= pokeApiService.getPokemon(name);
        String locAreaEncounters= null;
        ObjectMapper mapper= new ObjectMapper();

        try {
            JsonNode rootNode= mapper.readTree(response);
            JsonNode jobNode= rootNode.get(Constants.LOCATION_AREA_ENCOUNTERS);
            locAreaEncounters= mapper.writeValueAsString(jobNode);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return locAreaEncounters;
    }
}
