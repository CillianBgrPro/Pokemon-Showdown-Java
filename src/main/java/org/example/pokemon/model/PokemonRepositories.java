package org.example.pokemon.model;

import org.example.pokemon.Manager.DataBaseManager;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class PokemonRepositories {
    public List<Pokemon> getAllPokemon() {
        List<Pokemon> list = new ArrayList<>();
        String sql = "SELECT * FROM pokemon";

        try{
            Connection connection =
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }


}
