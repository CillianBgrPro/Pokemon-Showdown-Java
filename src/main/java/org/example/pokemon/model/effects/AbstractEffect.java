package org.example.pokemon.model.effects;

import org.example.pokemon.model.Pokemon;

public abstract class AbstractEffect {
    // La méthode reçoit le lanceur, la cible, et les dégâts infligés
    public abstract void apply(Pokemon user, Pokemon target, int damage);
}
