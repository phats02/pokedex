package com.ferb.Pokedex.repository;

import com.ferb.Pokedex.entity.Pokemon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PokemonRepository extends JpaRepository<Pokemon, Long> {
    @Query("SELECT p FROM Pokemon p WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<Pokemon> searchPokemonByName(@Param("name") String name);
}
