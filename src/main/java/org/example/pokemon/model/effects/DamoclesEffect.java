package org.example.pokemon.model.effects;

import org.example.pokemon.model.Pokemon;

public class DamoclesEffect extends AbstractEffect{
    @Override
    public void apply(Pokemon player, Pokemon target, int damage) {
        int recoil = damage / 3;
       player.setHp(Math.max(0,player.getHp() - recoil));
        System.out.println(player.getName() + " subit " + recoil + " de recul !");
    }
}
