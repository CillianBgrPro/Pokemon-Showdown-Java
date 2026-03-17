package org.example.pokemon.model.effects;

import org.example.pokemon.model.Pokemon;

public class SangsueEffect extends AbstractEffect{
    @Override
    public void apply(Pokemon user, Pokemon target, int damage) {
        int heal = damage / 2;
        user.setHp(Math.min(user.getMaxHp(), user.getHp() + heal));
        System.out.println(user.getName() + " regagne " + heal + " PV !");
    }
}
