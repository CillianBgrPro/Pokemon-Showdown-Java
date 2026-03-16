package org.example.pokemon.model;

import org.example.pokemon.model.Pokemon;

public abstract class AbstractItem {
    protected String name;
    protected String description;

    public AbstractItem(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() { return name; }
    public String getDescription() { return description; }
    public void onTurnEnd(Pokemon owner) {}
    public double getDamageMultiplier() { return 1.0; }
    public void onTakeDamage(Pokemon owner, int damage) {}

    @Override
    public String toString() {
        return name;
    }
}