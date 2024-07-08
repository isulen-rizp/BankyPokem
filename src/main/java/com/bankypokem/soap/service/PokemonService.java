package com.bankypokem.soap.service;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface PokemonService {

    @WebMethod
    String abilities(String name);

    @WebMethod
    int baseExperience(String name);

    @WebMethod
    String heldItems(String name);

    @WebMethod
    int id(String name);

    @WebMethod
    String name(String name);

    @WebMethod
    String locationAreaEncounters(String name);
}
