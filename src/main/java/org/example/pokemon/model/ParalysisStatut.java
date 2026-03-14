package org.example.pokemon.model;

public class ParalysisStatut extends AbstractStatuts{
    public ParalysisStatut(String name) {
        super(name);
    }

    protected boolean speedReduced = false;
    @Override
    public double getCoeffAttack() {
        return 1.0;
    }

    @Override
    public void effectStatut(Pokemon pokemon) {
        if(!speedReduced){
            pokemon.setSpeed(pokemon.getSpeed()/2);
            speedReduced = true;
        }
    }
}
