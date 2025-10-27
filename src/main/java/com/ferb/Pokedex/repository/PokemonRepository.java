package com.ferb.Pokedex.repository;

import com.ferb.Pokedex.entity.Pokemon;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface PokemonRepository extends JpaRepository<Pokemon, Long> {
    @Query("SELECT p FROM Pokemon p WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    Page<Pokemon> searchPokemonByName(@Param("name") String name, Pageable pageable);
}
