package com.bankypokem.soap.service.impl;

import com.bankypokem.soap.service.PokemonService;

import javax.jws.WebService;

@WebService(endpointInterface = "com.bankypokem.soap.service.PokemonService")
public class PokemonServiceImpl implements PokemonService {
    public String abilities(String name) {
        return null;
    }

    public int baseExperience(String name) {
        return 0;
    }

    public String[] heldItems(String name) {
        return new String[0];
    }

    public int id(String name) {
        return 0;
    }

    public String name(String name) {
        return null;
    }

    public String locationAreaEncounters(String name) {
        return null;
    }
}
