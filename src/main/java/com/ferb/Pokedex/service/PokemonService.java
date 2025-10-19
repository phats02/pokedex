package com.ferb.Pokedex.service;

import com.ferb.Pokedex.dto.PokemonResponse;
import com.ferb.Pokedex.entity.Pokemon;
import com.ferb.Pokedex.repository.PokemonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PokemonService {
    @Autowired
    private PokemonRepository pokemonRepository;

    public List<PokemonResponse> getAllPokemons() {
        return pokemonRepository.findAll().stream().map(this::mapToResponse).collect(Collectors.toList());
    }

    private  PokemonResponse mapToResponse (Pokemon pokemon) {
        return PokemonResponse
                .builder()
                .id(pokemon.getId())
                .maxHp(pokemon.getMaxHp())
                .maxCp(pokemon.getMaxCp())
                .attack(pokemon.getAttack())
                .defense(pokemon.getDefense())
                .isLegendary(pokemon.isLegendary())
                .type1(pokemon.getType1())
                .type2(pokemon.getType2())
                .build();
    }

}
