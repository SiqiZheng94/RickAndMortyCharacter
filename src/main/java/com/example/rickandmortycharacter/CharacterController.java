package com.example.rickandmortycharacter;

import jakarta.websocket.server.PathParam;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/characters")
public class CharacterController {

    @GetMapping("/test")
    public List<Result> test(){
        List<Result> characterList = Objects.requireNonNull(
                WebClient.create()
                        .get()
                        .uri("https://rickandmortyapi.com/api/character")
                        .retrieve()
                        .toEntityList(Result.class)
                        .block()
        ).getBody();
        return characterList;
    }
    @GetMapping()
    public List<Character> getAllCharacters(){

        Result result1 = Objects.requireNonNull(
                WebClient.create()
                        .get()
                        .uri("https://rickandmortyapi.com/api/character")
                        .retrieve()
                        .toEntity(Result.class)
                        .block()
        ).getBody();

        return result1.results();

    }
    @GetMapping("/{id}")
    public Character getById(@PathVariable String id){
        Character character=Objects.requireNonNull(
                WebClient.create()
                        .get()
                        .uri("https://rickandmortyapi.com/api/character/"+id)
                        .retrieve()
                        .toEntity(Character.class)
                        .block()
        ).getBody();
        return character;
    }
    @GetMapping("/search")
    public List<Character> searchByStatus(@RequestParam String status){
        Result result = Objects.requireNonNull(
                WebClient.create()
                        .get()
                        .uri("https://rickandmortyapi.com/api/character/")
                        .retrieve()
                        .toEntity(Result.class)
                        .block()
        ).getBody();
       return result.results().stream()
                .filter(character -> character.status().equals(status))
                .collect(Collectors.toList());

    }




    @GetMapping("/species")
    public long numberOfSpecies(@RequestParam String species){
        Result result = Objects.requireNonNull(
                WebClient.create()
                        .get()
                        .uri("https://rickandmortyapi.com/api/character/")
                        .retrieve()
                        .toEntity(Result.class)
                        .block()
        ).getBody();

       return result.results().stream()
                .filter(character -> character.species().equals(species))
               .count();
    }


}