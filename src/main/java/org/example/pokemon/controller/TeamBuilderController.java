package org.example.pokemon.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.example.pokemon.model.AbstractItem;
import org.example.pokemon.model.ItemRepository;
import org.example.pokemon.model.Pokemon;
import org.example.pokemon.model.PokemonRepository;

import java.util.List;

public class TeamBuilderController {

    @FXML private ListView<Pokemon> availableListView;
    @FXML private ListView<Pokemon> teamListView;
    @FXML private Button btnStartFight;
    @FXML private ComboBox<AbstractItem> itemComboBox;
    @FXML private TextArea itemDescriptionArea;

    private PokemonRepository repository = new PokemonRepository();
    private ItemRepository itemRepo = new ItemRepository();

    private ObservableList<Pokemon> teamItems = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        List<Pokemon> allPokemons = repository.findAll();
        availableListView.setItems(FXCollections.observableArrayList(allPokemons));
        itemComboBox.setItems(FXCollections.observableArrayList(itemRepo.findAll()));
        teamListView.setItems(teamItems);

        itemComboBox.getSelectionModel().selectedItemProperty().addListener((obs, oldVal,
                                                                             newVal) -> {
            if (newVal != null) {
                itemDescriptionArea.setText(newVal.getDescription());

                Pokemon selectedPoke = teamListView.getSelectionModel().getSelectedItem();
                if (selectedPoke != null) {
                    selectedPoke.setHeldItem(newVal);
                    System.out.println(selectedPoke.getName() + " tient maintenant : " + newVal.getName());
                }
            }
        });
    }

    @FXML
    private void handleAddPokemon() {
        Pokemon selected = availableListView.getSelectionModel().getSelectedItem();
        if (selected != null && teamItems.size() < 3) {
            teamItems.add(selected);
            updateStartButton();
        }
    }

    @FXML
    private void handleRemovePokemon() {
        Pokemon selected = teamListView.getSelectionModel().getSelectedItem();
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
        System.out.println("Lancement du combat !");
    }
}