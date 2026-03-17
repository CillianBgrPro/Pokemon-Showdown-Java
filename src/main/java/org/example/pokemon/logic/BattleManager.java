package org.example.pokemon.logic;

import org.example.pokemon.model.Attack;
import org.example.pokemon.model.Pokemon;
import java.util.Random;

public class BattleManager {
    private Pokemon playerActive;
    private Pokemon opponentActive;
    private Random random = new Random();

    public BattleManager(Pokemon player, Pokemon opponent) {
        this.playerActive = player;
        this.opponentActive = opponent;
    }

    public void executeTurn(Attack playerChoice) {
        int randomIndex = random.nextInt(opponentActive.getAttacks().size());
        Attack opponentChoice = opponentActive.getAttacks().get(randomIndex);

        TurnManager.gameTurn(playerActive, opponentActive, playerChoice, opponentChoice);
        applyEndOfTurnEffects();
    }

    private void applyEndOfTurnEffects() {
        if (playerActive.getHeldItem() != null) playerActive.getHeldItem().onTurnEnd(playerActive);
        if (opponentActive.getHeldItem() != null) opponentActive.getHeldItem().onTurnEnd(opponentActive);
    }

    public Pokemon getPlayerActive() { return playerActive; }
    public Pokemon getOpponentActive() { return opponentActive; }
}