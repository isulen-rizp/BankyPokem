package com.bankypokem.soap.service.impl;

import com.bankypokem.soap.SpringContext;
import com.bankypokem.soap.service.PokeApiService;
import com.bankypokem.soap.service.PokemonService;
import com.bankypokem.soap.util.Constants;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;

import javax.annotation.Resource;
import javax.jws.WebService;
import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;

@WebService(endpointInterface = "com.bankypokem.soap.service.PokemonService")
@Log4j2
public class PokemonServiceImpl implements PokemonService {

    private PokeApiService pokeApiService;

    public PokemonServiceImpl(){
        pokeApiService= SpringContext.getBean(PokeApiService.class);
    }

   @Resource
   private WebServiceContext webServiceContext;

    public String abilities(String name) {
        MessageContext mc=webServiceContext.getMessageContext();
        HttpServletRequest request= (HttpServletRequest)mc.get(MessageContext.SERVLET_REQUEST);
        com.sun.net.httpserver.HttpExchange server = (com.sun.net.httpserver.HttpExchange)     mc.get("com.sun.xml.internal.ws.http.exchange");

        String response= pokeApiService.getPokemon(name);
        String abilitiesResponse= null;
        ObjectMapper mapper= new ObjectMapper();

        log.info(response);
        try {
            JsonNode rootNode= mapper.readTree(response);
            JsonNode jobNode= rootNode.get(Constants.ABILITIES);
            log.info(jobNode.toPrettyString());
            abilitiesResponse= jobNode.toPrettyString();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return abilitiesResponse;
    }

    public int baseExperience(String name) {
        String response= pokeApiService.getPokemon(name);
        int baseExperience= 0;
        ObjectMapper mapper= new ObjectMapper();

        log.info(response);
        try {
            JsonNode rootNode= mapper.readTree(response);
            JsonNode jobNode= rootNode.get(Constants.BASE_EXPERIENCE);
            log.info(jobNode.toPrettyString());

            baseExperience= Integer.parseInt(jobNode.toPrettyString());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return baseExperience;
    }

    public String heldItems(String name) {
        String response= pokeApiService.getPokemon(name);
        String heldItems= null;
        ObjectMapper mapper= new ObjectMapper();

        log.info(response);
        try {
            JsonNode rootNode= mapper.readTree(response);
            JsonNode jobNode= rootNode.get(Constants.HELD_ITEMS);
            log.info(jobNode.toPrettyString());

            heldItems= jobNode.toPrettyString();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return heldItems;
    }

    public int id(String name) {
        String response= pokeApiService.getPokemon(name);
        int id= 0;
        ObjectMapper mapper= new ObjectMapper();

        log.info(response);
        try {
            JsonNode rootNode= mapper.readTree(response);
            JsonNode jobNode= rootNode.get(Constants.ID);
            log.info(jobNode.toPrettyString());

            id= Integer.parseInt(jobNode.toPrettyString());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return id;
    }

    public String name(String name) {
        String response= pokeApiService.getPokemon(name);
        String pokName= null;
        ObjectMapper mapper= new ObjectMapper();

        log.info(response);
        try {
            JsonNode rootNode= mapper.readTree(response);
            JsonNode jobNode= rootNode.get(Constants.NAME);
            log.info(jobNode.toPrettyString());

            pokName= jobNode.toPrettyString();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return pokName;
    }

    public String locationAreaEncounters(String name) {
        String response= pokeApiService.getPokemon(name);
        String locAreaEncounters= null;
        ObjectMapper mapper= new ObjectMapper();

        log.info(response);
        try {
            JsonNode rootNode= mapper.readTree(response);
            JsonNode jobNode= rootNode.get(Constants.LOCATION_AREA_ENCOUNTERS);
            log.info(jobNode.toPrettyString());

            locAreaEncounters= jobNode.toPrettyString();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return locAreaEncounters;
    }
}
