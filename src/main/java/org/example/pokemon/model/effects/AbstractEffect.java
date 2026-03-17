package org.example.pokemon.model.effects;

import org.example.pokemon.model.Pokemon;

public abstract class AbstractEffect {

    public abstract void apply(Pokemon user, Pokemon target, int damage);
}
