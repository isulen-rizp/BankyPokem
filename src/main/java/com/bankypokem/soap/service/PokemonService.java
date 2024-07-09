package com.bankypokem.soap.service;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface PokemonService {

    @WebMethod
    String abilities(String name);

    @WebMethod
    String baseExperience(String name);

    @WebMethod
    String heldItems(String name);

    @WebMethod
    String id(String name);

    @WebMethod
    String name(String name);

    @WebMethod
    String locationAreaEncounters(String name);
}
