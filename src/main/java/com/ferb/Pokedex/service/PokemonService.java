package com.ferb.Pokedex.service;

import com.ferb.Pokedex.dto.PaginationResponse;
import com.ferb.Pokedex.dto.PokemonResponse;
import com.ferb.Pokedex.entity.Pokemon;
import com.ferb.Pokedex.repository.PokemonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    public PaginationResponse<PokemonResponse> searchPokemon(String name, Pageable pageRequest){
        Page<Pokemon> queryResult = pokemonRepository.searchPokemonByName(name, pageRequest);
        return PaginationResponse.<PokemonResponse>builder().data(
                queryResult.getContent().stream().map(this::mapToResponse).collect(Collectors.toList()))
                .pageSize(queryResult.getPageable().getPageSize())
                .page(queryResult.getPageable().getPageNumber())
                .total(queryResult.getTotalElements()).build();
    }


    private  PokemonResponse mapToResponse (Pokemon pokemon) {
        return PokemonResponse
                .builder()
                .id(pokemon.getId())
                .name(pokemon.getName())
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
