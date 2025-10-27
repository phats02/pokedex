package com.ferb.Pokedex.controller;

import com.ferb.Pokedex.dto.PaginationResponse;
import com.ferb.Pokedex.dto.PokemonResponse;
import com.ferb.Pokedex.service.PokemonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    public PaginationResponse<PokemonResponse> searchPokemon(@RequestParam(value = "name", required = false) @NotBlank(message = "name is required") String nameSearch,
                                                             @RequestParam(defaultValue = "0")  @Min(value = 0, message = "size must be larger than 0") int page,
                                                             @RequestParam(defaultValue = "10") @Max(value = 100, message = "size must be smaller or equal 100") @Min(value = 0, message = "size must be larger than 0") int pageSize) {
        Pageable pageable= PageRequest.of(page, pageSize);
        return pokemonService.searchPokemon(nameSearch, pageable);
    }
}
