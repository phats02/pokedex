package com.ferb.Pokedex.service;

import com.ferb.Pokedex.entity.Pokemon;
import com.ferb.Pokedex.repository.PokemonRepository;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Component
@Slf4j
public class CsvDataLoader implements CommandLineRunner {

    @Autowired
    private PokemonRepository pokemonRepository;

    @Transactional
    public void run(String... args) throws Exception {
        log.info("Runn here");
        if (pokemonRepository.count() == 0) {
            log.info("Loading Pokemon data from CSV...");
            loadPokemonData();
            log.info("✅ Pokemon data loaded successfully!");
        } else {
            log.info("Pokemon data already exists. Skipping CSV import.");
        }
    }

    private void loadPokemonData() {
        try {
            FileReader filereader = new FileReader(new ClassPathResource("data/PokemonGOData.csv").getFile());
            CSVReader csvReader = new CSVReader(filereader);
            csvReader.readNext();

            Set<Pokemon> pokemons  = new HashSet<>();
            String[] record;
            int count = 0;

            while ((record = csvReader.readNext()) != null) {
                try {
                    Pokemon pokemon = Pokemon.builder()
                            .name(record[0].trim())
                            .maxCp(Integer.parseInt(record[1].replace(",", "")))
                            .maxHp(Integer.parseInt(record[2]))
                            .attack(Integer.parseInt(record[3]))
                            .defense(Integer.parseInt(record[4]))
                            .stamina(Integer.parseInt(record[5]))
                            .totalStats(Integer.parseInt(record[6]))
                            .isLegendary(record[7].equals("True"))
                            .type1(record[8])
                            .type2(record[9].equals("none") ? null: record[9])
                            .build();
                    pokemons.add(pokemon);
                    count++;
                } catch (Exception e) {
                    log.error("Error mapping CSV data", e);
                }


            }

            pokemonRepository.saveAll(pokemons);
            log.info("Pokemon loaded: ");
            pokemons.forEach((p) -> log.info(p.toString()));
            log.info("📊 Loaded {} pokemons from CSV", count);

        } catch (NumberFormatException
                 | IOException
                 | ArrayIndexOutOfBoundsException
                 | CsvValidationException e) {
            log.error("Error loading CSV data", e);
        }

    }

}
