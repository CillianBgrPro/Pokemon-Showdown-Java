package org.example.pokemon.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.example.pokemon.model.Pokemon;

public class BattleController {
    @FXML private Label playerPokemonName;
    @FXML private Label opponentPokemonName;
    @FXML private ImageView playerPokemonImage;
    @FXML private ImageView opponentPokemonImage;

    public void setBattleData(Pokemon playerPokemon, Pokemon opponent) {
        playerPokemonName.setText(playerPokemon.getName());
        opponentPokemonName.setText(opponent.getName());

//        if (playerPokemon.getImageUrl() != null) {
//            playerPokemonImage.setImage(new Image(getClass().getResourceAsStream(playerPokemon.getImageUrl())));
//        }
    }
}
