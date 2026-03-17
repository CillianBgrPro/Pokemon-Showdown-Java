package org.example.pokemon.model.effects;

import org.example.pokemon.model.AbstractStatuts;
import org.example.pokemon.model.Pokemon;

public class StatusEffect extends AbstractEffect {
    private AbstractStatuts statusToApply;
    private double chance;

    public StatusEffect(AbstractStatuts status, double chance) {
        this.statusToApply = status;
        this.chance = chance;
    }

    @Override
    public void apply(Pokemon user, Pokemon target, int damage) {
        if (Math.random() < this.chance) {
            target.setStatut(this.statusToApply);
        }
    }
}
