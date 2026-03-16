package org.example.pokemon.status;

import org.example.pokemon.model.AbstractStatuts;
import org.example.pokemon.model.Pokemon;

public class BurnStatut extends AbstractStatuts {

    public BurnStatut(){
        super(StatutName.Burn.name());
    }

    @Override
    public void effectStatut(Pokemon pokemon) {
        int degats = (int) Math.ceil(pokemon.getHp() / 16.0);
        pokemon.setHp(pokemon.getHp() - degats);


    }
    @Override
    public double getCoeffAttack(){
        return 0.5;
    }
}
