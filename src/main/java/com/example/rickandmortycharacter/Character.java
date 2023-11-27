package com.example.rickandmortycharacter;

import java.util.List;

public record Character(int id,
                        String name,
                        String species,
                        String status) {
}
