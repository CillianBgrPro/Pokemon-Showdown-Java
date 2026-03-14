package org.example.pokemon.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import org.example.pokemon.model.Pokemon;
import org.example.pokemon.model.PokemonRepositories;

import java.util.List;

public class TeamBuilderController {

    @FXML private ListView<String> availableListView;
    @FXML private ListView<String> teamListView;
    @FXML private Button btnStartFight;

    private PokemonRepositories repository = new PokemonRepositories();
    private ObservableList<String> teamItems = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        List<Pokemon> allPokemons = repository.findAll();

        ObservableList<String> names = FXCollections.observableArrayList();
        for (Pokemon p : allPokemons) {
            names.add(p.getName());
        }
        availableListView.setItems(names);

        teamListView.setItems(teamItems);
    }

    @FXML
    private void handleAddPokemon() {
        String selected = availableListView.getSelectionModel().getSelectedItem();
        if (selected != null && teamItems.size() < 3) {
            teamItems.add(selected);
            updateStartButton();
        }
    }

    @FXML
    private void handleRemovePokemon() {
        String selected = teamListView.getSelectionModel().getSelectedItem();
        if (selected != null) {
            teamItems.remove(selected);
            updateStartButton();
        }
    }

    private void updateStartButton() {
        btnStartFight.setDisable(teamItems.size() != 3);
    }

    @FXML
    private void handleStartFight() {
        System.out.println("Lancement du combat avec : " + teamItems);
    }
}