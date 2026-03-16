package org.example.pokemon.model.items;

import org.example.pokemon.model.AbstractItem;
import org.example.pokemon.model.Pokemon;

public class CeintureForceItem extends AbstractItem {
    private boolean used = false;

    public CeintureForceItem(String name, String description) {
        super(name, description);
    }

    @Override
    public void onTakeDamage(Pokemon owner, int damage) {
        if (!used && damage >= owner.getHp()) {
            owner.setHp(1);
            used = true;
            System.out.println("La Ceinture Force a permis à " + owner.getName() + " de survivre !");
        }
    }
}