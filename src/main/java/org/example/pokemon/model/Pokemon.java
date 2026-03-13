package org.example.pokemon.model;

public class Pokemon {
    private int id;
    private String name;
    private int hp;
    private int attack;
    private int defense;
    private int specialAttack;
    private int specialDefense;
    private int speed;
    private Type type1;
    private Type type2;
    private String imageUrl;

    public Pokemon(int id, String name, int hp, int attack, int defense,
                   int specialAttack, int specialDefense,
                   int speed, Type type1, Type type2, String imageUrl) {

        this.id = id;
        this.name = name;
        this.hp = hp;
        this.attack = attack;
        this.defense = defense;
        this.specialAttack = specialAttack;
        this.specialDefense = specialDefense;
        this.speed = speed;
        this.type1 = type1;
        this.type2 = type2;
        this.imageUrl = imageUrl;
    }


    public int getId() { return id; }
    public String getName() { return name; }
    public int getHp() { return hp; }
    public int getAttack() { return attack; }
    public int getDefense() { return defense; }
    public int getSpecialAttack() { return specialAttack; }
    public int getSpecialDefense() { return specialDefense; }
    public int getSpeed() { return speed; }
    public Type getType1() { return type1; }
    public Type getType2() { return type2; }
    public String getImageUrl() { return imageUrl; }

    public void setHp(int hp) {
        this.hp = Math.max(0, hp);
    }
}