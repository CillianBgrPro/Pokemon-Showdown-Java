package org.example.pokemon.Manager;

import org.example.pokemon.model.Attack;
import org.example.pokemon.model.Pokemon;
import java.util.ArrayList;
import java.util.List;

import static org.example.pokemon.logic.DamageCalculator.calculate;

public class TurnManager {

    public static List<String> gameTurn(Pokemon p1, Pokemon p2, Attack a1, Attack a2) {
        List<String> logs = new ArrayList<>();

        if (p1.getStatut() != null) p1.getStatut().effectStatut(p1);
        if (p2.getStatut() != null) p2.getStatut().effectStatut(p2);

        Pokemon fastest = Pokemon.getFastest(p1, p2);
        Pokemon slowest = Pokemon.getSlowest(p1, p2);
        Attack fastAtk = (fastest == p1) ? a1 : a2;
        Attack slowAtk = (slowest == p1) ? a1 : a2;

        logs.add(executeAttack(fastest, slowest, fastAtk));

        if (!slowest.isKO()) {
            logs.add(executeAttack(slowest, fastest, slowAtk));
        } else {
            logs.add(slowest.getName() + " est KO !");
        }

        return logs;
    }

    private static String executeAttack(Pokemon attacker, Pokemon target, Attack attack) {
        if (!attacker.canAttack) {
            attacker.canAttack = true; // reset pour le prochain tour
            return attacker.getName() + " est paralysé et ne peut pas attaquer !";
        }

        int damage = calculate(attacker, target, attack);
        target.setHp(target.getHp() - damage);

        String efficiency = "";
        return attacker.getName() + " utilise " + attack.getName() + " et inflige " + damage + " dégâts." + efficiency;
    }
}