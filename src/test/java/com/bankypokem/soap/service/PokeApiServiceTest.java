package com.bankypokem.soap.service;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class PokeApiServiceTest {

    @Test
    public void testGetPokemon(){
        RestTemplate mockRestTemplate= mock(RestTemplate.class);

        String mockJson="{\"name\":\"bulbasaur\"}";
        ResponseEntity<String> mockResponse= mock(ResponseEntity.class);

        when(mockRestTemplate.exchange(anyString(), any(HttpMethod.class), any(HttpEntity.class), eq(String.class))).thenReturn(mockResponse);
        when(mockResponse.getBody()).thenReturn(mockJson);
        when(mockResponse.getStatusCode()).thenReturn(HttpStatus.OK);

        PokeApiService service= new PokeApiService();
        service.setRestTemplate(mockRestTemplate);

        String result= service.getPokemon("bulbasaur");

        verify(mockRestTemplate, times(1)).exchange(anyString(), any(HttpMethod.class),  any(HttpEntity.class), eq(String.class));
        assertEquals(mockJson, result, "The getPokemon method should return the correct JSON");

    }
}
