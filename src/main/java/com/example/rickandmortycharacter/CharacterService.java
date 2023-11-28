package com.example.rickandmortycharacter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.Objects;

@Service
public class CharacterService {
    private WebClient webClient;

    public CharacterService(@Value("${basic.url}") String basicUrl) {
        this.webClient = WebClient.create(basicUrl);
    }

    public Character getCharacter(){
         return Objects.requireNonNull(
                webClient
                        .get()
                        .uri("/character/2")
                        .retrieve()
                        .toEntity(Character.class)
                        .block()
        ).getBody();

    }
}
