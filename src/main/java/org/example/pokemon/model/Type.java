package org.example.pokemon.model;

import java.util.HashMap;
import java.util.Map;

public class Type {
    private String name;
    private Map<String, Double> effectiveness;

    public enum TypeName {
        ACIER, EAU, ELECTRIK, FEU, PLANTE, PSY, ROCHE, SOL, NORMAL, VOL,
        POISON, INSECTE, SPECTRE, DRAGON, TENEBRES, FEE, GLACE, COMBAT
    }

    public Type(String name) {
        this.name = name;
        this.effectiveness = new HashMap<>();
    }

    public static Type fromString(String name) {
        if (name == null || name.trim().isEmpty()) {
            return null;
        }

        return new Type(name);
    }

    public void addEfficacity(String nomTypeAdverse, double multiplicateur) {
        this.effectiveness.put(nomTypeAdverse.toUpperCase(), multiplicateur);
    }

    public double getDamageMultiplier(String enemyTypeName) {
        return effectiveness.getOrDefault(enemyTypeName.toUpperCase(), 1.0);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    public TypeInteractions getInteractions() {
        return interactions;
    }
}