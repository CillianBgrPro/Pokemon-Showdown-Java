package org.example.pokemon.model;

import java.util.HashMap;
import java.util.Map;

public class Type {
    private String name;
    private Map<String, Double> effectiveness;

    public enum TypeName {
        Acier,
        Eau,
        Électrik,
        Feu,
        Plante,
        Psy,
        Roche,
        Sol
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
}