package org.example.pokemon.model;

import org.example.pokemon.model.effects.AbstractEffect;

public class Attack {
    private int id;
    private String name;
    private int damage;
    private Type type;
    private String category;
    private AbstractEffect secondaryEffect;

    public Attack(int id, String name, int damage, Type type,
                  String category, AbstractEffect secondaryEffect) {
        this.id = id;
        this.name = name;
        this.damage = damage;
        this.type = type;
        this.category = category;
        this.secondaryEffect = secondaryEffect;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public int getDamage() { return damage; }
    public Type getType() { return type; }
    public String getCategory() { return category; }
    public AbstractEffect getSecondaryEffect() { return secondaryEffect; }

    @Override
    public String toString() {
        return name + " (" + type.getName() + ")";
    }
}


