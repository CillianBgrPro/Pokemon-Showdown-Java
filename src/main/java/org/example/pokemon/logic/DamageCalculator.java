package org.example.pokemon.logic;

import org.example.pokemon.model.Attack;
import org.example.pokemon.model.Pokemon;
import java.util.Random;

public class DamageCalculator {
    private static final Random random = new Random();

    public static int calculate(Pokemon attack, Pokemon defense, Attack category) {
        if (category.getCategory().equalsIgnoreCase("STATUS")) {
            return 0;
        }

        double atkStat;
        double defStat;

        if (category.getCategory().equalsIgnoreCase("PHYSICAL")) {
            atkStat = attack.getAttack();
            defStat = defense.getDefense();
        } else {
            atkStat = attack.getSpecialAttack();
            defStat = defense.getSpecialDefense();
        }

        double typeMultiplier = 1.0;

        double randomFactor = 0.85 + (1.0 - 0.85) * random.nextDouble();

        double damage = category.getDamage() * (atkStat / defStat) * typeMultiplier * randomFactor;

        return (int) Math.round(damage);
    }
}