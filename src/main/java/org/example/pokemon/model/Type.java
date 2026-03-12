package org.example.pokemon.model;

import org.example.pokemon.model.TypeInteractions;

//package org.example.pokemon.model;
//
//public class Type {
//    private String name;
//
//    public Type(String name) {
//        this.name = name;
//    }
//}
public class Type {
    private String nom;
    private TypeInteractions interactions;

    public Type(String nom) {
        this.nom = nom;
        this.interactions = new TypeInteractions(); // Utilise le constructeur par défaut
    }

    public void ajouterEfficacite(String nomTypeAdverse, double multiplicateur) {
        // Elle prend l'info et la donne à la "Map" qui est dans TypeInteraction
        this.interactions.setEffectiveness(nomTypeAdverse, multiplicateur);
    }

    public String getNom() {
        return nom;
    }
}