package com.ferb.Pokedex.controller;

import com.ferb.Pokedex.dto.PokemonResponse;
import com.ferb.Pokedex.service.PokemonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Validated
@Tag(name = "Pokemon APIs", description = "All the Pokedex APIs here")
public class PokemonController {

    @Autowired
    private PokemonService pokemonService;

    @GetMapping("/all")
    @Operation(summary = "Get all pokemons")
    public List<PokemonResponse> getAll() {
        return pokemonService.getAllPokemons();
    }
    @GetMapping("/search")
    @Operation(summary = "Search pokemons")
    public  List<PokemonResponse> searchPokemon(@RequestParam(value = "name", required = false) @NotBlank(message = "name is required") String nameSearch) {
        return pokemonService.searchPokemon(nameSearch);
    }
}
