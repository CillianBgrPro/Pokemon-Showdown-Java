package org.example.pokemon.Manager;

import org.example.pokemon.model.Attack;
import org.example.pokemon.model.Pokemon;
import org.example.pokemon.logic.DamageCalculator;
import java.util.ArrayList;
import java.util.List;

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
        }

        return logs;
    }

    private static String executeAttack(Pokemon attacker, Pokemon target, Attack attack) {
        if (!attacker.canAttack) {
            attacker.canAttack = true;
            return attacker.getName() + " est paralysé et ne peut pas attaquer !";
        }

        int damage = DamageCalculator.calculate(attacker, target, attack);
        target.setHp(target.getHp() - damage);

        double mult = target.getType1().getDamageMultiplier(attack.getType().getName());
        if (target.getType2() != null) mult *= target.getType2().getDamageMultiplier(attack.getType().getName());

        String effectiveness = "";
        if (mult > 1) effectiveness = " C'est très efficace !";
        else if (mult < 1 && mult > 0) effectiveness = " Ce n'est pas très efficace...";
        else if (mult == 0) effectiveness = " Ça n'a aucun effet...";

        String log = attacker.getName() + " utilise " + attack.getName() + " !" + effectiveness + " (" + damage + " dégâts)";

        if (attack.getSecondaryEffect() != null) {
            attack.getSecondaryEffect().apply(attacker, target, damage);
        }
        if (target.getHeldItem() != null) {
            target.getHeldItem().onTakeDamage(target, damage);
        }

        return log;
    }
}