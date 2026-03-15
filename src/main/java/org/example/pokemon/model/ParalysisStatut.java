package org.example.pokemon.model;

public class ParalysisStatut extends AbstractStatuts{
    public ParalysisStatut() {
        super(StatutName.Paralysie.name());
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
        if (Math.random() < 0.25) {
            pokemon.canAttack = false;
        } else {
            pokemon.canAttack = true;
        }
    }
}
