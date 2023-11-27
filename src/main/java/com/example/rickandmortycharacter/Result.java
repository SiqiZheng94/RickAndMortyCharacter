package com.example.rickandmortycharacter;

import java.util.ArrayList;
import java.util.List;

public record Result(List<Character> results) {

    public List<Character> addList(List<Character> characterList){

        for (Character character:characterList) {
            results.add(character);
        }
        return results;
    }
}
