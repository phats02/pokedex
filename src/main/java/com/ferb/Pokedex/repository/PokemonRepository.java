package com.ferb.Pokedex.repository;

import com.ferb.Pokedex.entity.Pokemon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PokemonRepository extends JpaRepository<Pokemon, Long> {
}
