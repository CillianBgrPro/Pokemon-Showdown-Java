//package org.example.pokemon;
//
//import org.example.pokemon.logic.DamageCalculator;
//import org.example.pokemon.model.Attack;
//import org.example.pokemon.model.Pokemon;
//import org.example.pokemon.model.Type;
//
//
//class MainTest {
//    public static void main(String[] args) {
//        Type feu = new Type("Feu");
//        Type eau = new Type("Eau");
//
//        Pokemon dracaufeu = new Pokemon(21, "Dracaufeu", 78, 84, 78, 109,
//                85, 100, feu, null, "sprites/006.png");
//
//        Pokemon tortank = new Pokemon(22, "Tortank", 79, 83, 100, 85,
//                105, 78, eau, null, "sprites/009.png");
//
//        Attack lanceFlammes = new Attack(1, "Lance-Flammes", 90,
//                feu, "SPECIAL", "Brûlure");
//
//        int degats = DamageCalculator.calculate(dracaufeu, tortank, lanceFlammes);
//
//        System.out.println(dracaufeu.getName() + " utilise " + lanceFlammes.getName() +
//                " sur " + tortank.getName() + " et inflige " + degats + " dégâts.");
//    }
//}

package org.example.pokemon;

import org.example.pokemon.logic.TurnManager;
import org.example.pokemon.status.ParalysisStatut;
import org.example.pokemon.model.Pokemon;
import org.example.pokemon.model.Type;

class MainTest {
    public static void main(String[] args) {
        Type feu = new Type("Feu");
        Type eau = new Type("Eau");

        Pokemon dracaufeu = new Pokemon(21, "Dracaufeu", 78, 84, 78, 109,
                85, 100, feu, null, "sprites/006.png");

        Pokemon tortank = new Pokemon(22, "Tortank", 79, 83, 100, 85,
                105, 78, eau, null, "sprites/009.png");


        dracaufeu.setStatut(new ParalysisStatut());
        System.out.println(dracaufeu.getName() + " est paralyse avant le combat !");

        TurnManager manager = new TurnManager();

        manager.gameTurn(dracaufeu, tortank);

        System.out.println("PV restants de Dracaufeu : " + dracaufeu.getHp());
        System.out.println("PV restants de Tortank : " + tortank.getHp());
    }
}