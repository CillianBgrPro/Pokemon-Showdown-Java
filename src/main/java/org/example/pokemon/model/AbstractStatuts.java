package org.example.pokemon.model;

public abstract class AbstractStatuts {
    protected String name;


    public enum StatutName{
        Paralysie,
        Poison,
        Burn
    }



    public AbstractStatuts(String name) {
        this.name = name;
    }

    public String getName() {return name;}

    public double getCoeffAttack(){
        return 1.0;
    }

    public abstract void effectStatut(Pokemon pokemon);
}
