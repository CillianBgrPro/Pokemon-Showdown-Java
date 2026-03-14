package org.example.pokemon.model;

import org.example.pokemon.Manager.DataBaseManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PokemonRepositories {

    public List<Pokemon> findAll() {
        List<Pokemon> list = new ArrayList<>();
        String query = "SELECT * FROM Pokemons";

        try (Connection conn = DataBaseManager.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                String type1Name = rs.getString("type1");
                String type2Name = rs.getString("type2");

                Pokemon p = new Pokemon(
                        rs.getInt("id"),
                        rs.getString("pokemon_name"),
                        rs.getInt("hp"),
                        rs.getInt("attack"),
                        rs.getInt("defense"),
                        rs.getInt("sp_attack"),
                        rs.getInt("sp_defense"),
                        rs.getInt("speed"),
                        new Type(type1Name),
                        type2Name != null ? new Type(type2Name) : null,
                        rs.getString("sprite_path")
                );

                list.add(p);
            }
        } catch (Exception e) {
            System.err.println("Erreur lors de la récupération des Pokémon : " + e.getMessage());
            e.printStackTrace();
        }

        return list;
    }
}