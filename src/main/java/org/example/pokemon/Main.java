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
import org.example.pokemon.model.ParalysisStatut;
import org.example.pokemon.model.Pokemon;
import org.example.pokemon.model.Type;
import org.example.pokemon.model.ParalysisStatut;

class MainTest {
    public static void main(String[] args) {
        Type feu = new Type("Feu");
        Type eau = new Type("Eau");

        // Dracaufeu (Vitesse 100)
        Pokemon dracaufeu = new Pokemon(21, "Dracaufeu", 78, 84, 78, 109,
                85, 100, feu, null, "sprites/006.png");

        // Tortank (Vitesse 78)
        Pokemon tortank = new Pokemon(22, "Tortank", 79, 83, 100, 85,
                105, 78, eau, null, "sprites/009.png");

        // --- PHASE DE TEST ---

        // 1. On paralyse Dracaufeu.
        // Sa vitesse de 100 va passer à 50 dans le TurnManager.
        dracaufeu.setStatut(new ParalysisStatut());
        System.out.println(dracaufeu.getName() + " est paralyse avant le combat !");

        // 2. On crée le TurnManager
        TurnManager manager = new TurnManager();

        // 3. On lance le tour
        // Ici, Tortank devrait attaquer en PREMIER car 78 > 50
        manager.gameTurn(dracaufeu, tortank);

        // 4. On affiche les PV restants pour voir l'impact
        System.out.println("PV restants de Dracaufeu : " + dracaufeu.getHp());
        System.out.println("PV restants de Tortank : " + tortank.getHp());
    }
}