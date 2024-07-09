package com.bankypokem.soap.controller;

import com.bankypokem.soap.service.PokemonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pokemon-rest")
@Api(value = "Pokemon API")
public class PokeRestController {

    @Autowired
    private PokemonService pokemonServiceImpl;

    public PokeRestController(){
    }

    @GetMapping("/{name}/abilities")
    @ApiOperation(value = "Obtener abilities de Pokemon")
    public String abilities(@PathVariable String name){
        return pokemonServiceImpl.abilities(name);
    }

    @GetMapping("/{name}/heldItems")
    @ApiOperation(value = "Obtener heldItems de Pokemon")
    public String heldItems(@PathVariable String name){
        return pokemonServiceImpl.heldItems(name);
    }

    @GetMapping("/{name}/name")
    @ApiOperation(value = "Obtener name de Pokemon")
    public String name(@PathVariable String name){
        return pokemonServiceImpl.name(name);
    }

    @GetMapping("/{name}/id")
    @ApiOperation(value = "Obtener Id de Pokemon")
    public int id(@PathVariable String name){
        return pokemonServiceImpl.id(name);
    }

    @GetMapping("/{name}/baseExperince")
    @ApiOperation(value = "Obtener BaseExperience de Pokemon")
    public int baseExperience(@PathVariable String name){
        return pokemonServiceImpl.baseExperience(name);
    }

    @GetMapping("/{name}/locationAreaEncounters")
    @ApiOperation(value = "Obtener LocationAreaEncounters de Pokemon")
    public String locationAreaEncounters(@PathVariable String name){
        return pokemonServiceImpl.locationAreaEncounters(name);
    }

}
