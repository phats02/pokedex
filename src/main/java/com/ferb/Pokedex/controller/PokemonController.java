package com.ferb.Pokedex.controller;

import com.ferb.Pokedex.dto.PokemonResponse;
import com.ferb.Pokedex.entity.Pokemon;
import com.ferb.Pokedex.repository.PokemonRepository;
import com.ferb.Pokedex.service.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PokemonController {

    @Autowired
    private PokemonService pokemonService;

    @GetMapping("/all")
    public List<PokemonResponse> getAll() {
        return pokemonService.getAllPokemons();
    }
}
