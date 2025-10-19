package com.ferb.Pokedex.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "pokemons")
@ToString
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Pokemon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int maxCp;

    @Column(nullable = false)
    private int maxHp;

    @Column(nullable = false)
    private int attack;

    @Column(nullable = false)
    private int defense;

    @Column(nullable = false)
    private int stamina;

    @Column(nullable = false)
    private int totalStats;

    @Column(nullable = false)
    private boolean isLegendary;

    @NonNull
    @Column(nullable = false)
    private String type1;

    @Column(nullable = true)
    private String type2;
}
