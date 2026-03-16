package org.example.pokemon.logic;

import org.example.pokemon.model.Attack;
import org.example.pokemon.model.Pokemon;

import java.util.Random;

import static org.example.pokemon.logic.TurnManager.gameTurn;

public class BattleManager {
   private Pokemon player;
   private Pokemon opponent;
   private boolean isBattleOver;

   public BattleManager(Pokemon player, Pokemon opponent){
       this.player = player;
       this.opponent = opponent;
       this.isBattleOver = false;
   }

   public void Battle(Attack playerAttack){
        if (isBattleOver){
            return;
        }
       Attack opponentAttack = opponent.getAttacks().get(
               new Random().nextInt(opponent.getAttacks().size())
       );
        gameTurn(player,opponent,playerAttack,opponentAttack);
        checkWinCondition();
   }
    private void checkWinCondition() {
        if (player.getHp() <= 0 || opponent.getHp() <= 0) {
            isBattleOver = true;

        }
    }

    public boolean getIsBattleOver(){return isBattleOver;}
}

