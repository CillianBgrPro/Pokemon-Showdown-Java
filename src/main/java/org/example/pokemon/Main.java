////package org.example.pokemon;
////
////import org.example.pokemon.logic.DamageCalculator;
////import org.example.pokemon.model.Attack;
////import org.example.pokemon.model.Pokemon;
////import org.example.pokemon.model.Type;
////
////
////class MainTest {
////    public static void main(String[] args) {
////        Type feu = new Type("Feu");
////        Type eau = new Type("Eau");
////
////        Pokemon dracaufeu = new Pokemon(21, "Dracaufeu", 78, 84, 78, 109,
////                85, 100, feu, null, "sprites/006.png");
////
////        Pokemon tortank = new Pokemon(22, "Tortank", 79, 83, 100, 85,
////                105, 78, eau, null, "sprites/009.png");
////
////        Attack lanceFlammes = new Attack(1, "Lance-Flammes", 90,
////                feu, "SPECIAL", "Brûlure");
////
////        int degats = DamageCalculator.calculate(dracaufeu, tortank, lanceFlammes);
////
////        System.out.println(dracaufeu.getName() + " utilise " + lanceFlammes.getName() +
////                " sur " + tortank.getName() + " et inflige " + degats + " dégâts.");
////    }
////}
//
//package org.example.pokemon;
//
//import org.example.pokemon.logic.TurnManager;
//import org.example.pokemon.status.ParalysisStatut;
//import org.example.pokemon.model.Pokemon;
//import org.example.pokemon.model.Type;
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
//
//        dracaufeu.setStatut(new ParalysisStatut());
//        System.out.println(dracaufeu.getName() + " est paralyse avant le combat !");
//
//        TurnManager manager = new TurnManager();
//
//        manager.gameTurn(dracaufeu, tortank);
//
//        System.out.println("PV restants de Dracaufeu : " + dracaufeu.getHp());
//        System.out.println("PV restants de Tortank : " + tortank.getHp());
//    }
//}

package org.example.pokemon;

import org.example.pokemon.logic.BattleManager;
import org.example.pokemon.model.Attack;
import org.example.pokemon.model.Pokemon;
import org.example.pokemon.model.Type;
import java.util.ArrayList;

class MainTest {
    public static void main(String[] args) {
        // 1. Création des types
        Type feu = new Type("Feu");
        Type eau = new Type("Eau");

        // 2. Création des Pokémon (on simule ce qui viendrait de ta BDD)
        Pokemon dracaufeu = new Pokemon(1, "Dracaufeu", 100, 80, 80, 100, 80, 100, feu, null, "sprite1.png");
        Pokemon tortank = new Pokemon(2, "Tortank", 100, 80, 100, 80, 100, 78, eau, null, "sprite2.png");

        // 3. On leur donne au moins une attaque (sinon le Random de BattleManager va planter)
        Attack charge = new Attack(1, "Charge", 40, new Type("Normal"), "PHYSICAL", "NONE");
        dracaufeu.addAttack(charge);
        tortank.addAttack(charge);

        // 4. Initialisation du BattleManager
        BattleManager battle = new BattleManager(dracaufeu, tortank);

        System.out.println("--- DÉBUT DU TEST DE BATTLE ---");

        // 5. Simulation de 3 clics sur le bouton "Attaquer"
        for (int i = 1; i <= 3; i++) {
            if (!battle.getIsBattleOver()) {
                System.out.println("\n--- Clic Bouton n°" + i + " ---");
                battle.Battle(charge); // On simule le clic avec l'attaque "charge"

                System.out.println("PV Dracaufeu : " + dracaufeu.getHp());
                System.out.println("PV Tortank : " + tortank.getHp());
            }
        }

        if (battle.getIsBattleOver()) {
            System.out.println("\nLe combat s'est fini prématurément !");
        } else {
            System.out.println("\nTest réussi : les tours s'enchaînent bien.");
        }
    }
}