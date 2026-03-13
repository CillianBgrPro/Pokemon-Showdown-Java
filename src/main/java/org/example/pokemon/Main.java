package org.example.pokemon;

import org.example.pokemon.logic.DamageCalculator;
import org.example.pokemon.model.Attack;
import org.example.pokemon.model.Pokemon;
import org.example.pokemon.model.Type;


public class MainTest {


    public static void main(String[] args) {
        Pokemon dracaufeu = new Pokemon(21, "Dracaufeu", 78, 84, 78, 109,
                85, 100, "Feu", null, "sprites/006.png");

        Pokemon tortank = new Pokemon(22, "Tortank", 79, 83, 100, 85,
                105, 78, "Eau", null, "sprites/009.png");

        Attack lanceFlammes = new Attack(1, "Lance-Flammes", 90,
                "Feu", "SPECIAL", "Brûlure");

        int degats = DamageCalculator.calculate(dracaufeu, tortank, lanceFlammes);
        System.out.println(dracaufeu.getName() + " utilise " + lanceFlammes.getName() +
                " sur " + tortank.getName() + " et inflige " + degats + " dégâts.");
    }
}