package com.bankypokem.soap.service.impl;

import com.bankypokem.soap.SpringContextTestUtils;
import com.bankypokem.soap.repository.RequestDataRepository;
import com.bankypokem.soap.service.PokeApiService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class PokemonServiceImplTest {

    @Test
    void testAbilities() throws Exception{
        PokeApiService mockPokeApiService= mock(PokeApiService.class);
        RequestDataRepository mockRequestDataRepository= mock(RequestDataRepository.class);

        ResponseEntity<String> mockResponseEntity = mock(ResponseEntity.class);
        String mockJson="{\"abilities\":[{\"ability\":{\"name\":\"overgrow\",\"url\":\"https://pokeapi.co/api/v2/ability/65/\"},\"is_hidden\":false,\"slot\":1},{\"ability\":{\"name\":\"chlorophyll\",\"url\":\"https://pokeapi.co/api/v2/ability/34/\"},\"is_hidden\":true,\"slot\":3}]}";

        when(mockResponseEntity.getBody()).thenReturn(mockJson);
        when(mockResponseEntity.getStatusCode()).thenReturn(HttpStatus.OK);
        when(mockPokeApiService.getPokemon("bulbasaur")).thenReturn(mockResponseEntity);

        SpringContextTestUtils.setupSpringContext(mockPokeApiService, mockRequestDataRepository);
        PokemonServiceImpl service= new PokemonServiceImpl();
        String result= service.abilities("bulbasaur");

        verify(mockPokeApiService, times(1)).getPokemon("bulbasaur");

        ObjectMapper mapper= new ObjectMapper();
        String expected= mapper.writeValueAsString(mapper.readTree(mockJson).get("abilities"));
        assertEquals(expected, result, "The abilities method should return the correct abilities");
    }

    @Test
    void testBaseExperience() throws Exception{
        PokeApiService mockPokeApiService= mock(PokeApiService.class);
        RequestDataRepository mockRequestDataRepository= mock(RequestDataRepository.class);


        ResponseEntity<String> mockResponseEntity = mock(ResponseEntity.class);
        String mockJson="{\"base_experience\":64}";

        when(mockResponseEntity.getBody()).thenReturn(mockJson);
        when(mockResponseEntity.getStatusCode()).thenReturn(HttpStatus.OK);
        when(mockPokeApiService.getPokemon("bulbasaur")).thenReturn(mockResponseEntity);

        SpringContextTestUtils.setupSpringContext(mockPokeApiService, mockRequestDataRepository);
        PokemonServiceImpl service= new PokemonServiceImpl();

        String result= service.baseExperience("bulbasaur");

        verify(mockPokeApiService, times(1)).getPokemon("bulbasaur");

        ObjectMapper mapper= new ObjectMapper();
        String expected= mapper.writeValueAsString(mapper.readTree(mockJson).get("base_experience"));
        assertEquals(expected, result, "The baseExperience method should return the correct base experience");
    }


    @Test
    void testHeldItems() throws Exception{
        PokeApiService mockPokeApiService= mock(PokeApiService.class);
        RequestDataRepository mockRequestDataRepository= mock(RequestDataRepository.class);

        ResponseEntity<String> mockResponseEntity = mock(ResponseEntity.class);
        String mockJson="{\"hel_items\":[{\"item\":{\"name\":\"oran-berry\",\"url\":\"https://pokeapi.co/api/v2/item/132/\"},\"version_details\":[{\"rarity\":50,\"version\":{\"name\":\"ruby\",\"url\":\"https://pokeapi.co/api/v2/version/7/\"}},{\"rarity\":50,\"version\":{\"name\":\"sapphire\",\"url\":\"https://pokeapi.co/api/v2/version/8/\"}},{\"rarity\":50,\"version\":{\"name\":\"emerald\",\"url\":\"https://pokeapi.co/api/v2/version/9/\"}},{\"rarity\":50,\"version\":{\"name\":\"diamond\",\"url\":\"https://pokeapi.co/api/v2/version/12/\"}},{\"rarity\":50,\"version\":{\"name\":\"pearl\",\"url\":\"https://pokeapi.co/api/v2/version/13/\"}},{\"rarity\":50,\"version\":{\"name\":\"platinum\",\"url\":\"https://pokeapi.co/api/v2/version/14/\"}},{\"rarity\":50,\"version\":{\"name\":\"heartgold\",\"url\":\"https://pokeapi.co/api/v2/version/15/\"}},{\"rarity\":50,\"version\":{\"name\":\"soulsilver\",\"url\":\"https://pokeapi.co/api/v2/version/16/\"}},{\"rarity\":50,\"version\":{\"name\":\"black\",\"url\":\"https://pokeapi.co/api/v2/version/17/\"}},{\"rarity\":50,\"version\":{\"name\":\"white\",\"url\":\"https://pokeapi.co/api/v2/version/18/\"}}]},{\"item\":{\"name\":\"light-ball\",\"url\":\"https://pokeapi.co/api/v2/item/213/\"},\"version_details\":[{\"rarity\":5,\"version\":{\"name\":\"ruby\",\"url\":\"https://pokeapi.co/api/v2/version/7/\"}},{\"rarity\":5,\"version\":{\"name\":\"sapphire\",\"url\":\"https://pokeapi.co/api/v2/version/8/\"}},{\"rarity\":5,\"version\":{\"name\":\"emerald\",\"url\":\"https://pokeapi.co/api/v2/version/9/\"}},{\"rarity\":5,\"version\":{\"name\":\"diamond\",\"url\":\"https://pokeapi.co/api/v2/version/12/\"}},{\"rarity\":5,\"version\":{\"name\":\"pearl\",\"url\":\"https://pokeapi.co/api/v2/version/13/\"}},{\"rarity\":5,\"version\":{\"name\":\"platinum\",\"url\":\"https://pokeapi.co/api/v2/version/14/\"}},{\"rarity\":5,\"version\":{\"name\":\"heartgold\",\"url\":\"https://pokeapi.co/api/v2/version/15/\"}},{\"rarity\":5,\"version\":{\"name\":\"soulsilver\",\"url\":\"https://pokeapi.co/api/v2/version/16/\"}},{\"rarity\":1,\"version\":{\"name\":\"black\",\"url\":\"https://pokeapi.co/api/v2/version/17/\"}},{\"rarity\":1,\"version\":{\"name\":\"white\",\"url\":\"https://pokeapi.co/api/v2/version/18/\"}},{\"rarity\":5,\"version\":{\"name\":\"black-2\",\"url\":\"https://pokeapi.co/api/v2/version/21/\"}},{\"rarity\":5,\"version\":{\"name\":\"white-2\",\"url\":\"https://pokeapi.co/api/v2/version/22/\"}},{\"rarity\":5,\"version\":{\"name\":\"x\",\"url\":\"https://pokeapi.co/api/v2/version/23/\"}},{\"rarity\":5,\"version\":{\"name\":\"y\",\"url\":\"https://pokeapi.co/api/v2/version/24/\"}},{\"rarity\":5,\"version\":{\"name\":\"omega-ruby\",\"url\":\"https://pokeapi.co/api/v2/version/25/\"}},{\"rarity\":5,\"version\":{\"name\":\"alpha-sapphire\",\"url\":\"https://pokeapi.co/api/v2/version/26/\"}},{\"rarity\":5,\"version\":{\"name\":\"sun\",\"url\":\"https://pokeapi.co/api/v2/version/27/\"}},{\"rarity\":5,\"version\":{\"name\":\"moon\",\"url\":\"https://pokeapi.co/api/v2/version/28/\"}},{\"rarity\":5,\"version\":{\"name\":\"ultra-sun\",\"url\":\"https://pokeapi.co/api/v2/version/29/\"}},{\"rarity\":5,\"version\":{\"name\":\"ultra-moon\",\"url\":\"https://pokeapi.co/api/v2/version/30/\"}}]}]}";

        when(mockResponseEntity.getBody()).thenReturn(mockJson);
        when(mockResponseEntity.getStatusCode()).thenReturn(HttpStatus.OK);
        when(mockPokeApiService.getPokemon("pikachu")).thenReturn(mockResponseEntity);

        SpringContextTestUtils.setupSpringContext(mockPokeApiService, mockRequestDataRepository);
        PokemonServiceImpl service= new PokemonServiceImpl();
        String result= service.heldItems("pikachu");

        verify(mockPokeApiService, times(1)).getPokemon("pikachu");

        ObjectMapper mapper= new ObjectMapper();
        String expected= mapper.writeValueAsString(mapper.readTree(mockJson).get("held_items"));
        assertEquals(expected, result, "The heldItems method should return the correct held items");
    }


    @Test
    void testLocationAreaEncounters() throws Exception{
        PokeApiService mockPokeApiService= mock(PokeApiService.class);
        RequestDataRepository mockRequestDataRepository= mock(RequestDataRepository.class);

        ResponseEntity<String> mockResponseEntity = mock(ResponseEntity.class);
        String mockJson="{\"location_area_encounters\":\"https://pokeapi.co/api/v2/pokemon/25/encounters\"}";

        when(mockResponseEntity.getBody()).thenReturn(mockJson);
        when(mockResponseEntity.getStatusCode()).thenReturn(HttpStatus.OK);
        when(mockPokeApiService.getPokemon("pikachu")).thenReturn(mockResponseEntity);

        SpringContextTestUtils.setupSpringContext(mockPokeApiService, mockRequestDataRepository);
        PokemonServiceImpl service= new PokemonServiceImpl();
        String result= service.locationAreaEncounters("pikachu");

        verify(mockPokeApiService, times(1)).getPokemon("pikachu");

        ObjectMapper mapper= new ObjectMapper();
        String expected= mapper.writeValueAsString(mapper.readTree(mockJson).get("location_area_encounters"));
        assertEquals(expected, result, "The locationAreaEncounters method should return the correct location area encounters");
    }


    @Test
    void testId() throws Exception{
        PokeApiService mockPokeApiService= mock(PokeApiService.class);
        RequestDataRepository mockRequestDataRepository= mock(RequestDataRepository.class);

        ResponseEntity<String> mockResponseEntity = mock(ResponseEntity.class);
        String mockJson="{\"id\":1}";

        when(mockResponseEntity.getBody()).thenReturn(mockJson);
        when(mockResponseEntity.getStatusCode()).thenReturn(HttpStatus.OK);
        when(mockPokeApiService.getPokemon("bulbasaur")).thenReturn(mockResponseEntity);

        SpringContextTestUtils.setupSpringContext(mockPokeApiService, mockRequestDataRepository);
        PokemonServiceImpl service= new PokemonServiceImpl();
        String result= service.id("bulbasaur");

        verify(mockPokeApiService, times(1)).getPokemon("bulbasaur");

        ObjectMapper mapper= new ObjectMapper();
        String expected= mapper.writeValueAsString(mapper.readTree(mockJson).get("id"));
        assertEquals(expected, result, "The id method should return the correct id");
    }


    @Test
    void testName() throws Exception{
        PokeApiService mockPokeApiService= mock(PokeApiService.class);
        RequestDataRepository mockRequestDataRepository= mock(RequestDataRepository.class);

        ResponseEntity<String> mockResponseEntity = mock(ResponseEntity.class);
        String mockJson="{\"name\":\"bulbasaur\"}";

        when(mockResponseEntity.getBody()).thenReturn(mockJson);
        when(mockResponseEntity.getStatusCode()).thenReturn(HttpStatus.OK);
        when(mockPokeApiService.getPokemon("bulbasaur")).thenReturn(mockResponseEntity);

        SpringContextTestUtils.setupSpringContext(mockPokeApiService, mockRequestDataRepository);
        PokemonServiceImpl service= new PokemonServiceImpl();
        String result= service.name("bulbasaur");

        verify(mockPokeApiService, times(1)).getPokemon("bulbasaur");

        ObjectMapper mapper= new ObjectMapper();
        String expected= mapper.writeValueAsString(mapper.readTree(mockJson).get("name"));
        assertEquals(expected, result, "The name method should return the correct name");
    }
}
