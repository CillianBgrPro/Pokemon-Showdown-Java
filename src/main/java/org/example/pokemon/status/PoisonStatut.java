package org.example.pokemon.status;

import org.example.pokemon.model.AbstractStatuts;
import org.example.pokemon.model.Pokemon;

public class PoisonStatut extends AbstractStatuts {


    public PoisonStatut() {
        super(StatutName.Poison.name());
    }

    @Override
    public double getCoeffAttack() {
        return 1.0;
    }

    @Override
    public void effectStatut(Pokemon pokemon) {
//        pokemon.setHp(pokemon.getHp() *7/8); problème lorsque l'on a 1hp
        int degats = (int) Math.ceil(pokemon.getHp() / 8.0);
        pokemon.setHp(pokemon.getHp() - degats);
    }
}
