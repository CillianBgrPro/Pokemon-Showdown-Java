package org.example.pokemon.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.example.pokemon.logic.TurnManager;
import org.example.pokemon.model.Attack;
import org.example.pokemon.model.Pokemon;

import java.io.InputStream;
import java.util.List;
import java.util.Random;

public class BattleController {
    @FXML private Label playerPokemonName, cpuPokemonName;
    @FXML private Label playerHpLabel, cpuHpLabel;
    @FXML private ProgressBar playerHpBar, cpuHpBar;
    @FXML private ImageView playerPokemonImage, cpuPokemonImage;
    @FXML private Button move1, move2, move3, move4;
    @FXML private TextArea battleLog;

    private List<Pokemon> playerTeam;
    private List<Pokemon> cpuTeam;
    private Pokemon activePlayer;
    private Pokemon activeCpu;

    public void setBattleData(List<Pokemon> playerTeam, List<Pokemon> cpuTeam) {
        this.playerTeam = playerTeam;
        this.cpuTeam = cpuTeam;
        this.activePlayer = playerTeam.get(0);
        this.activeCpu = cpuTeam.get(0);

        battleLog.setText("Le combat commence ! " + activePlayer.getName() + " vs " + activeCpu.getName() + "\n");
        updateUI();
    }

    private void updateUI() {

        playerPokemonName.setText(activePlayer.getName());
        cpuPokemonName.setText(activeCpu.getName());

        playerHpLabel.setText(activePlayer.getHp() + " / " + activePlayer.getMaxHp());
        cpuHpLabel.setText(activeCpu.getHp() + " / " + activeCpu.getMaxHp());


        playerHpBar.setProgress((double) activePlayer.getHp() / activePlayer.getMaxHp());
        cpuHpBar.setProgress((double) activeCpu.getHp() / activeCpu.getMaxHp());


        List<Attack> attacks = activePlayer.getAttacks();
        Button[] buttons = {move1, move2, move3, move4};

        for (int i = 0; i < buttons.length; i++) {
            if (i < attacks.size()) {
                buttons[i].setText(attacks.get(i).getName());
                buttons[i].setDisable(false);
            } else {
                buttons[i].setText("-");
                buttons[i].setDisable(true);
            }
        }

        loadPokemonImage(activePlayer, playerPokemonImage);
        loadPokemonImage(activeCpu, cpuPokemonImage);
    }

    private void loadPokemonImage(Pokemon pokemon, ImageView imageView) {
        try {

            String path = "/org/example/pokemon/" + pokemon.getImageUrl();
            InputStream is = getClass().getResourceAsStream(path);

            if (is != null) {
                imageView.setImage(new Image(is));
            } else {
                System.err.println("Fichier image introuvable : " + path);
            }
        } catch (Exception e) {
            System.err.println("Erreur lors du chargement de l'image pour " + pokemon.getName());
        }
    }

    @FXML
    private void handleAttack(javafx.event.ActionEvent event) {
        Button btn = (Button) event.getSource();
        int index = Integer.parseInt(btn.getId().substring(4)) - 1;

        if (index >= activePlayer.getAttacks().size()) return;

        Attack playerAtk = activePlayer.getAttacks().get(index);
        Attack cpuAtk = activeCpu.getAttacks().get(new Random().nextInt(activeCpu.getAttacks().size()));
        List<String> turnResults = TurnManager.gameTurn(activePlayer, activeCpu, playerAtk, cpuAtk);
        for (String message : turnResults) {
            battleLog.appendText(message + "\n");
        }

        updateUI();
        checkDeath();
        battleLog.setScrollTop(Double.MAX_VALUE);
    }

    private void checkDeath() {
        if (activeCpu.isKO()) {
            battleLog.appendText(activeCpu.getName() + " adverse est KO !\n");
            replaceCpuPokemon();
        }
        if (activePlayer.isKO()) {
            battleLog.appendText(activePlayer.getName() + " est KO !\n");
            replacePlayerPokemon();
        }
    }

    private void replaceCpuPokemon() {
        for (Pokemon p : cpuTeam) {
            if (!p.isKO()) {
                activeCpu = p;
                battleLog.appendText("L'adversaire envoie " + p.getName() + " !\n");
                updateUI();
                return;
            }
        }
        battleLog.appendText("VICTOIRE ! Vous avez gagné.\n");
        disableButtons();
    }

    private void replacePlayerPokemon() {
        for (Pokemon p : playerTeam) {
            if (!p.isKO()) {
                activePlayer = p;
                battleLog.appendText("Allez " + p.getName() + " !\n");
                updateUI();
                return;
            }
        }
        battleLog.appendText("DÉFAITE... Tous vos Pokémon sont KO.\n");
        disableButtons();
    }

    private void disableButtons() {
        move1.setDisable(true); move2.setDisable(true);
        move3.setDisable(true); move4.setDisable(true);
    }
}