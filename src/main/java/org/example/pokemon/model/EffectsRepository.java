package org.example.pokemon.model;

import org.example.pokemon.model.effects.AbstractEffect;
import org.example.pokemon.model.effects.DamoclesEffect;
import org.example.pokemon.model.effects.SangsueEffect;
import org.example.pokemon.model.effects.StatusEffect;
import org.example.pokemon.status.BurnStatut;
import org.example.pokemon.status.ParalysisStatut;

public class EffectsRepository {
    public AbstractEffect getEffectByMoveName(String moveName) {
        return switch (moveName) {
            case "Damoclès" -> new DamoclesEffect();
            case "Giga-Sangsue" -> new SangsueEffect();
            case "Lance-Flammes", "Poing de Feu" -> new StatusEffect(new BurnStatut(), 0.10);
            case "Tonnerre" -> new StatusEffect(new ParalysisStatut(), 0.10);
            default -> null; // Les attaques sans effet
        };
    }
}
