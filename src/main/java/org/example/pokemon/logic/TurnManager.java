package org.example.pokemon.logic;

import org.example.pokemon.model.Attack;
import org.example.pokemon.model.Pokemon;
import org.example.pokemon.model.Type;

import static org.example.pokemon.logic.DamageCalculator.calculate;
import static org.example.pokemon.model.Pokemon.getFastest;
import static org.example.pokemon.model.Pokemon.getSlowest;

public class TurnManager {

    public void gameTurn(Pokemon p1, Pokemon p2){
        if (p1.getStatut() != null) {
            p1.getStatut().effectStatut(p1);
        }
        if (p2.getStatut() != null) {
            p2.getStatut().effectStatut(p2);
        }
        Pokemon fastest = getFastest(p1,p2);
        Pokemon slowest = getSlowest(p1,p2);
        Type solType = new Type("SOL");
        Attack charge = new Attack(1, "Charge", 40,
                solType, "PHYSICAL", "NONE");
        int damage = calculate(fastest,slowest, charge);

        executeAttack(fastest,slowest,charge);

        if (slowest.getHp() > 0){
            executeAttack(slowest,fastest,charge);
        }
    }

    private void executeAttack(Pokemon attacker, Pokemon target, Attack attack) {

        if (attacker.getHp() > 0 && attacker.canAttack) {
            int damage = calculate(attacker, target, attack);
            target.setHp(target.getHp() - damage);

            System.out.println(attacker.getName() + " utilise " + attack.getName() + " : " + damage + " degats.");
        }
        else if (attacker.getHp() > 0 && !attacker.canAttack) {
            System.out.println(attacker.getName() + " est paralyse !");
        }

        attacker.canAttack = true;
    }

}
