package org.example.pokemon.model;

public class Attack {
    private int id;
    private String name;
    private int damage;
    private Type type;
    private String category;
    private String secondaryEffect;

    public Attack(int id, String name, int damage, Type type,
                  String category, String secondaryEffect) {
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
    public String getSecondaryEffect() { return secondaryEffect; }
}


