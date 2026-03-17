package org.example.pokemon.logic;

import org.example.pokemon.model.Attack;
import org.example.pokemon.model.Pokemon;
import org.example.pokemon.model.items.LunetteChoixItem;
import java.util.Random;

public class DamageCalculator {
    private static final Random random = new Random();

    public static int calculate(Pokemon attacker, Pokemon defender, Attack move) {
        if (move.getCategory().equalsIgnoreCase("STATUS")) {
            return 0;
        }

        double atkStat;
        double defStat;

        double itemMultiplier = 1.0;
        if (attacker.getHeldItem() != null) {
            itemMultiplier *= attacker.getHeldItem().getDamageMultiplier();


            if (move.getCategory().equalsIgnoreCase("SPECIAL") &&
                    attacker.getHeldItem() instanceof LunetteChoixItem) {
                itemMultiplier *= 1.5;
            }
        }

        if (move.getCategory().equalsIgnoreCase("PHYSICAL")) {
            atkStat = attacker.getAttack();

            if (attacker.getStatut() != null) {
                atkStat *= attacker.getStatut().getCoeffAttack();
            }
            defStat = defender.getDefense();
        } else {
            atkStat = attacker.getSpecialAttack();
            defStat = defender.getSpecialDefense();
        }
        double typeMultiplier = getEffectiveness(move, defender);
        double randomFactor = 0.85 + (1.0 - 0.85) * random.nextDouble();

        double damage = move.getDamage() * (atkStat / defStat) * typeMultiplier * randomFactor * itemMultiplier;

        return (int) Math.round(damage);
    }

    private static double getEffectiveness(Attack move, Pokemon defender) {
        double multiplier = move.getType().getDamageMultiplier(defender.getType1().getName());

        if (defender.getType2() != null) {
            multiplier *= move.getType().getDamageMultiplier(defender.getType2().getName());
        }

        return multiplier;
    }

}