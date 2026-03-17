package org.example.pokemon.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.example.pokemon.model.*;

import java.io.IOException;
import java.util.List;

public class TeamBuilderController {

    @FXML private ListView<Pokemon> availableListView;
    @FXML private ListView<Pokemon> teamListView;
    @FXML private Button btnStartFight;

    @FXML private ComboBox<AbstractItem> itemComboBox;
    @FXML private TextArea itemDescriptionArea;

    @FXML private ComboBox<Attack> move1Combo, move2Combo, move3Combo, move4Combo;

    private PokemonRepository pokemonRepo = new PokemonRepository();
    private ItemRepository itemRepo = new ItemRepository();
    private AttackRepository attackRepo = new AttackRepository(); // Tu dois créer cette classe

    private ObservableList<Pokemon> teamItems = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        availableListView.setItems(FXCollections.observableArrayList(pokemonRepo.findAll()));
        itemComboBox.setItems(FXCollections.observableArrayList(itemRepo.findAll()));
        teamListView.setItems(teamItems);

        teamListView.getSelectionModel().selectedItemProperty().addListener((obs,
                                                                             oldVal, newVal) -> {
            updateConfigurationUI(newVal);
        });

        itemComboBox.getSelectionModel().selectedItemProperty().addListener((obs,
                                                                             oldVal, newVal) -> {
            Pokemon selected = teamListView.getSelectionModel().getSelectedItem();
            if (selected != null && newVal != null) {
                selected.setHeldItem(newVal);
                itemDescriptionArea.setText(newVal.getDescription());
            }
        });

        setupMoveComboHandler(move1Combo, 0);
        setupMoveComboHandler(move2Combo, 1);
        setupMoveComboHandler(move3Combo, 2);
        setupMoveComboHandler(move4Combo, 3);
    }

    private void updateConfigurationUI(Pokemon p) {
        if (p == null) {
            disableConfiguration(true);
            return;
        }
        disableConfiguration(false);

        List<Attack> learnableMoves = attackRepo.findMovesByPokemonId(p.getId());
        ObservableList<Attack> movesObs = FXCollections.observableArrayList(learnableMoves);

        move1Combo.setItems(movesObs);
        move2Combo.setItems(movesObs);
        move3Combo.setItems(movesObs);
        move4Combo.setItems(movesObs);

        if (p.getHeldItem() != null) {
            itemComboBox.getSelectionModel().select(p.getHeldItem());
            itemDescriptionArea.setText(p.getHeldItem().getDescription());
        } else {
            itemComboBox.getSelectionModel().clearSelection();
            itemDescriptionArea.setText("");
        }
    }

    private void setupMoveComboHandler(ComboBox<Attack> combo, int slot) {
        combo.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            Pokemon selected = teamListView.getSelectionModel().getSelectedItem();
            if (selected != null && newVal != null) {
                selected.setMove(slot, newVal);
            }
        });
    }

    private void disableConfiguration(boolean disable) {
        move1Combo.setDisable(disable);
        move2Combo.setDisable(disable);
        move3Combo.setDisable(disable);
        move4Combo.setDisable(disable);
        itemComboBox.setDisable(disable);
    }

    @FXML
    private void handleAddPokemon() {
        Pokemon selected = availableListView.getSelectionModel().getSelectedItem();
        if (selected != null && teamItems.size() < 3) {
            Pokemon copy = new Pokemon(
                    selected.getId(), selected.getName(), selected.getHp(),
                    selected.getAttack(), selected.getDefense(), selected.getSpecialAttack(),
                    selected.getSpecialDefense(), selected.getSpeed(),
                    selected.getType1(), selected.getType2(), selected.getImageUrl()
            );
            teamItems.add(copy);
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
        btnStartFight.setDisable(teamItems.size() < 3);
    }

    @FXML
    private void handleStartFight() {
        if(teamItems.size() < 3){
            return;
        }

        Pokemon selectedLeader = teamListView.getSelectionModel().getSelectedItem();
        if (selectedLeader == null) {
            selectedLeader = teamItems.get(0);
            System.out.println("Aucun leader choisi, on envoie " + selectedLeader.getName() + " par défaut.");
        } else {
            System.out.println("Combat lancé avec " + selectedLeader.getName() + " en première ligne !");
        }

        try {
            System.out.println("Tentative de changement de scène...");

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/pokemon/view/battle-view.fxml"));
            Parent root = loader.load();

            BattleController battleController = loader.getController();
            // On lui envoie notre Pokémon leader (et un adversaire de test)
            battleController.setBattleData(selectedLeader, teamItems.get(1));
            Stage stage = (Stage) btnStartFight.getScene().getWindow();
            stage.setScene(new Scene(root, 800, 600));
            stage.show();

        } catch (IOException e) {
            System.err.println("Erreur de chargement du FXML : " + e.getMessage());
            e.printStackTrace();
        }

        System.out.println("Lancement du combat avec une équipe de " + teamItems.size() + " Pokémon !");
    }
}