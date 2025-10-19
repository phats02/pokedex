package com.ferb.Pokedex.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PokemonResponse {
    private Long id;
    private String name;
    private int maxCp;
    private int maxHp;
    private int attack;
    private int defense;
    private int stamina;
    private int totalStats;
    private boolean isLegendary;
    private String type1;
    private String type2;
}
