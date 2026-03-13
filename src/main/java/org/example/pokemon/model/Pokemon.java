package org.example.pokemon.model;

import java.util.ArrayList;

public class Pokemon {
    private int id;
    private String name;
    private int hp;
    private int attackStat;
    private ArrayList<SpecialAttack> attacks;
    private int defense;
    private int attackSpeStat;
    private ArrayList<SpecialAttack> attackSpes;
    private int specialDefense;
    private int speed;
    private Type type1;
    private Type type2;
    private String imageUrl;

    public Pokemon(int id, String name, int hp, ArrayList<SpecialAttack> attacks, int defense,
                   int attackStat, int attackSpeStat,ArrayList<SpecialAttack> attackSpes , int specialDefense,
                   int speed, Type type1, Type type2, String imageUrl) {

        this.id = id;
        this.name = name;
        this.hp = hp;
        this.attacks = attacks;
        this.attackStat = attackStat;
        this.attackSpeStat =attackSpeStat;
        this.defense = defense;
        this.attackSpes = attackSpes;
        this.specialDefense = specialDefense;
        this.speed = speed;
        this.type1 = type1;
        this.type2 = type2;
        this.imageUrl = imageUrl;
    }


    public int getId() { return id; }
    public String getName() { return name; }
    public int getHp() { return hp; }
    public ArrayList<SpecialAttack> getAttack() { return attacks; }
    public int getDefense() { return defense; }
    public ArrayList<SpecialAttack> getSpecialAttack() { return attackSpes; }
    public int getSpecialDefense() { return specialDefense; }
    public int getSpeed() { return speed; }
    public Type getType1() { return type1; }
    public Type getType2() { return type2; }
    public String getImageUrl() { return imageUrl; }
    public int getAttackSpeStat() {return attackSpeStat;}
    public int getAttackStat() {return attackStat;}
    public void setHp(int hp) {
        this.hp = Math.max(0, hp);
    }
}