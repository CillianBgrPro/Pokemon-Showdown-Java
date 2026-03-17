package org.example.pokemon.logic;

import org.example.pokemon.model.Attack;
import org.example.pokemon.model.Pokemon;
import org.example.pokemon.model.Type;

import static org.example.pokemon.logic.DamageCalculator.calculate;
import static org.example.pokemon.model.Pokemon.getFastest;
import static org.example.pokemon.model.Pokemon.getSlowest;

public class TurnManager {

    public static void gameTurn(Pokemon p1, Pokemon p2, Attack a1, Attack a2 ){
        if (p1.getStatut() != null) {
            p1.getStatut().effectStatut(p1);
        }
        if (p2.getStatut() != null) {
            p2.getStatut().effectStatut(p2);
        }

        if (p1.getHp() <= 0 || p2.getHp() <= 0) {
            System.out.println("Un Pokémon est KO à cause de son statut !");
            return;
        }
        Pokemon fastest = getFastest(p1,p2);
        Pokemon slowest = getSlowest(p1,p2);

        executeAttack(fastest, slowest, (fastest == p1) ? a1 : a2);

        if (slowest.getHp() > 0){
            executeAttack(slowest, fastest, (slowest == p1) ? a1 : a2);
        }
    }

    private static void executeAttack(Pokemon attacker, Pokemon target, Attack attack) {

        if (attacker.getHp() > 0 && attacker.canAttack) {
            int damage = calculate(attacker, target, attack);
            target.setHp(target.getHp() - damage);

            System.out.println(attacker.getName() + " utilise " + attack.getName() + " : " + damage + " degats.");
            if (attack.getSecondaryEffect() != null && target.getHp() >= 0) {
                attack.getSecondaryEffect().apply(attacker, target, damage);
            }
        }
        else if (attacker.getHp() > 0 && !attacker.canAttack) {
            System.out.println(attacker.getName() + " est paralyse !");
        }

        attacker.canAttack = true;
    }

}
