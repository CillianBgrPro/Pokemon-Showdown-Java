package org.example.pokemon.model;

import org.example.pokemon.Manager.DataBaseManager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PokemonRepositories {
    public List<Pokemon> getAllPokemon() {
        List<Pokemon> list = new ArrayList<>();
        String query = "SELECT * FROM pokemon";

        try{
            Connection conn = DataBaseManager.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                Pokemon p = new Pokemon(
                        rs.getInt("id"),
                        rs.getString("pokemon_name"),
                        rs.getInt("hp"),
                        rs.getInt("attack"),
                        rs.getInt("defense"),
                        rs.getInt("sp_attack"),
                        rs.getInt("sp_defense"),
                        rs.getInt("speed"),
                        rs.getString("type1"),
                        rs.getString("type2"),
                        rs.getString("sprite_path")
                );
                list.add(p);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }


}
