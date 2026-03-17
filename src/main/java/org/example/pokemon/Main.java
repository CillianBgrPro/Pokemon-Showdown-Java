package org.example.pokemon;

import org.example.pokemon.logic.DamageCalculator;
import org.example.pokemon.model.Attack;
import org.example.pokemon.model.Pokemon;

//////package org.example.pokemon;
//////
//////import org.example.pokemon.logic.DamageCalculator;
//////import org.example.pokemon.model.Attack;
//////import org.example.pokemon.model.Pokemon;
//////import org.example.pokemon.model.Type;
//////
//////
//////class MainTest {
//////    public static void main(String[] args) {
//////        Type feu = new Type("Feu");
//////        Type eau = new Type("Eau");
//////
//////        Pokemon dracaufeu = new Pokemon(21, "Dracaufeu", 78, 84, 78, 109,
//////                85, 100, feu, null, "sprites/006.png");
//////
//////        Pokemon tortank = new Pokemon(22, "Tortank", 79, 83, 100, 85,
//////                105, 78, eau, null, "sprites/009.png");
//////
//////        Attack lanceFlammes = new Attack(1, "Lance-Flammes", 90,
//////                feu, "SPECIAL", "Brûlure");
//////
//////        int degats = DamageCalculator.calculate(dracaufeu, tortank, lanceFlammes);
//////
//////        System.out.println(dracaufeu.getName() + " utilise " + lanceFlammes.getName() +
//////                " sur " + tortank.getName() + " et inflige " + degats + " dégâts.");
//////    }
//////}
////
////package org.example.pokemon;
////
////import org.example.pokemon.logic.TurnManager;
////import org.example.pokemon.status.ParalysisStatut;
////import org.example.pokemon.model.Pokemon;
////import org.example.pokemon.model.Type;
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
////
////        dracaufeu.setStatut(new ParalysisStatut());
////        System.out.println(dracaufeu.getName() + " est paralyse avant le combat !");
////
////        TurnManager manager = new TurnManager();
////
////        manager.gameTurn(dracaufeu, tortank);
////
////        System.out.println("PV restants de Dracaufeu : " + dracaufeu.getHp());
////        System.out.println("PV restants de Tortank : " + tortank.getHp());
////    }
////}
//
//package org.example.pokemon;
//
//import org.example.pokemon.logic.BattleManager;
//import org.example.pokemon.model.Attack;
//import org.example.pokemon.model.Pokemon;
//import org.example.pokemon.model.Type;
//import java.util.ArrayList;
//
//class MainTest {
//    public static void main(String[] args) {
//        // 1. Création des types
//        Type feu = new Type("Feu");
//        Type eau = new Type("Eau");
//
//        // 2. Création des Pokémon (on simule ce qui viendrait de ta BDD)
//        Pokemon dracaufeu = new Pokemon(1, "Dracaufeu", 100, 80, 80, 100, 80, 100, feu, null, "sprite1.png");
//        Pokemon tortank = new Pokemon(2, "Tortank", 100, 80, 100, 80, 100, 78, eau, null, "sprite2.png");
//
//        // 3. On leur donne au moins une attaque (sinon le Random de BattleManager va planter)
//        Attack charge = new Attack(1, "Charge", 40, new Type("Normal"), "PHYSICAL", null);
//        dracaufeu.addAttack(charge);
//        tortank.addAttack(charge);
//
//        // 4. Initialisation du BattleManager
//        BattleManager battle = new BattleManager(dracaufeu, tortank);
//
//        System.out.println("--- DÉBUT DU TEST DE BATTLE ---");
//
//        // 5. Simulation de 3 clics sur le bouton "Attaquer"
//        for (int i = 1; i <= 3; i++) {
//            if (!battle.getIsBattleOver()) {
//                System.out.println("\n--- Clic Bouton n°" + i + " ---");
//                battle.Battle(charge); // On simule le clic avec l'attaque "charge"
//
//                System.out.println("PV Dracaufeu : " + dracaufeu.getHp());
//                System.out.println("PV Tortank : " + tortank.getHp());
//            }
//        }
//
//        if (battle.getIsBattleOver()) {
//            System.out.println("\nLe combat s'est fini prématurément !");
//        } else {
//            System.out.println("\nTest réussi : les tours s'enchaînent bien.");
//        }
//    }
//}




import org.example.pokemon.model.*;
import org.example.pokemon.logic.*;
import org.example.pokemon.model.items.OrbeVieItem;
import org.example.pokemon.model.effects.AbstractEffect;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== POKÉMON SHOWDOWN : TEST TECHNIQUE ===\n");

        // 1. Chargement des Types (Efficacité)
        TypeRepository typeRepo = new TypeRepository();
        Map<String, Type> allTypes = typeRepo.findAllEffectiveness();
        System.out.println("[1] Types chargés : " + allTypes.size() + " types trouvés en BDD.");

        // 2. Préparation des Types pour le combat
        Type feu = allTypes.get("Feu");
        Type plante = allTypes.get("Plante");
        Type normal = allTypes.getOrDefault("NORMAL", new Type("Normal"));

        if (feu == null || plante == null) {
            System.err.println("ERREUR : Vérifie ta table 'Effectiveness' en BDD !");
            return;
        }

        // 3. Création des Pokémon (Stats basées sur ton modèle)
        Pokemon dracaufeu = new Pokemon(1, "Dracaufeu", 200, 100, 80, 150, 80, 100, feu, null, "");
        dracaufeu.setHeldItem(new OrbeVieItem("Orbe Vie", "Booste les dégâts de 1.3x"));

        Pokemon bulbizarre = new Pokemon(2, "Bulbizarre", 150, 50, 50, 50, 50, 45, plante, null, "");

        // 4. Attribution des attaques
        // On simule l'effet via un effectRepository (ou manuellement pour le test)
        Attack lanceFlammes = new Attack(1, "Lance-Flammes", 90, feu, "SPECIAL", null);
        Attack charge = new Attack(2, "Charge", 40, normal, "PHYSICAL", null);

        dracaufeu.addAttack(lanceFlammes);
        bulbizarre.addAttack(charge);

        // 5. Lancement du Gestionnaire de Combat
        BattleManager battle = new BattleManager(dracaufeu, bulbizarre);

        System.out.println("\n--- DÉBUT DU DUEL ---");
        System.out.println(dracaufeu.getName() + " vs " + bulbizarre.getName());

        // Simulation de 2 tours
        for (int i = 1; i <= 2; i++) {
            if (!battle.getIsBattleOver()) {
                System.out.println("\n--- TOUR " + i + " ---");
                battle.Battle(lanceFlammes); // Dracaufeu utilise Lance-Flammes

                System.out.println("PV " + dracaufeu.getName() + " : " + dracaufeu.getHp());
                System.out.println("PV " + bulbizarre.getName() + " : " + bulbizarre.getHp());
                System.out.println("Statut cible : " + (bulbizarre.getStatut() != null ? bulbizarre.getStatut().getName() : "Aucun"));
            }
        }

        System.out.println("\n=== FIN DU TEST ===");
    }
}