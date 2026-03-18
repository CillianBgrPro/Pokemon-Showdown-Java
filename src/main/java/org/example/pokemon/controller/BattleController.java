package org.example.pokemon.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.example.pokemon.Manager.TurnManager;
import org.example.pokemon.Manager.SoundManager;
import org.example.pokemon.logic.DamageCalculator;
import org.example.pokemon.model.Attack;
import org.example.pokemon.model.Pokemon;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Random;

public class BattleController {
    @FXML private Label playerPokemonName, cpuPokemonName;
    @FXML private Label playerHpLabel, cpuHpLabel;
    @FXML private ProgressBar playerHpBar, cpuHpBar;
    @FXML private ImageView playerPokemonImage, cpuPokemonImage;
    @FXML private Button move1, move2, move3, move4;
    @FXML private Button btnPoke0, btnPoke1, btnPoke2;
    @FXML private Button btnBack;
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
        Button[] moveButtons = {move1, move2, move3, move4};
        for (int i = 0; i < 4; i++) {
            if (i < attacks.size() && !activePlayer.isKO()) {
                moveButtons[i].setText(attacks.get(i).getName());
                moveButtons[i].setDisable(false);
            } else {
                moveButtons[i].setText("-");
                moveButtons[i].setDisable(true);
            }
        }

        Button[] teamButtons = {btnPoke0, btnPoke1, btnPoke2};
        for (int i = 0; i < playerTeam.size(); i++) {
            Pokemon p = playerTeam.get(i);
            teamButtons[i].setText(p.getName() + " (" + p.getHp() + " HP)");
            teamButtons[i].setDisable(p == activePlayer || p.isKO());
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
            }
        } catch (Exception e) {
            System.err.println("Erreur image : " + pokemon.getName());
        }
    }

    @FXML
    private void handleAttack(ActionEvent event) {
        Button btn = (Button) event.getSource();
        int index = Integer.parseInt(btn.getId().substring(4)) - 1;

        if (index >= activePlayer.getAttacks().size()) return;

        Attack playerAtk = activePlayer.getAttacks().get(index);
        Attack cpuAtk = activeCpu.getAttacks().get(new Random().nextInt(activeCpu.getAttacks().size()));

        SoundManager.play("attack.wav");

        battleLog.appendText("\n--- TOUR SUIVANT ---\n");
        List<String> turnResults = TurnManager.gameTurn(activePlayer, activeCpu, playerAtk, cpuAtk);

        for (String message : turnResults) {
            battleLog.appendText(message + "\n");
            if (message.contains("très efficace")) {
                SoundManager.play("effective.wav");
            }
        }

        updateUI();
        checkDeath();
        battleLog.setScrollTop(Double.MAX_VALUE);
    }

    @FXML
    private void handleSwitch(ActionEvent event) {
        Button btn = (Button) event.getSource();
        int index = Integer.parseInt(btn.getId().substring(7));
        Pokemon nextPoke = playerTeam.get(index);

        if (nextPoke != null && !nextPoke.isKO()) {
            battleLog.appendText("\n" + activePlayer.getName() + ", reviens ! \n");
            activePlayer = nextPoke;
            battleLog.appendText("En avant, " + activePlayer.getName() + " ! \n");

            Attack cpuAtk = activeCpu.getAttacks().get(new Random().nextInt(activeCpu.getAttacks().size()));
            battleLog.appendText("L'adversaire profite du changement pour frapper !\n");

            int damage = DamageCalculator.calculate(activeCpu, activePlayer, cpuAtk);
            activePlayer.setHp(activePlayer.getHp() - damage);
            battleLog.appendText(activeCpu.getName() + " utilise " + cpuAtk.getName() + " (" + damage + " dégâts).\n");

            SoundManager.play("attack.wav");

            updateUI();
            checkDeath();
            battleLog.setScrollTop(Double.MAX_VALUE);
        }
    }

    @FXML
    private void handleBackToHome() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/pokemon/view/home-view.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) btnBack.getScene().getWindow();
            stage.setScene(new Scene(root, 800, 600));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void checkDeath() {
        if (activeCpu.isKO()) {
            battleLog.appendText(activeCpu.getName() + " adverse est KO !\n");
            SoundManager.play("faint.wav");
            replaceCpuPokemon();
        }
        if (activePlayer.isKO()) {
            battleLog.appendText(activePlayer.getName() + " est KO !\n");
            SoundManager.play("faint.wav");
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
        disableButtons();

        boolean hasAlive = false;
        for (Pokemon p : playerTeam) {
            if (!p.isKO()) {
                hasAlive = true;
                break;
            }
        }

        if (hasAlive) {
            battleLog.appendText("Choisissez un autre Pokémon pour continuer !\n");
            updateUI();
        } else {
            battleLog.appendText("DÉFAITE... Tous vos Pokémon sont KO.\n");
        }
    }

    private void disableButtons() {
        move1.setDisable(true); move2.setDisable(true);
        move3.setDisable(true); move4.setDisable(true);
    }
}