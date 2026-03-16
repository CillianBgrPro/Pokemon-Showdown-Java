package org.example.pokemon.model.items;

import org.example.pokemon.model.AbstractItem;
import org.example.pokemon.model.Pokemon;

public class OrbeVieItem extends AbstractItem {
    public OrbeVieItem(String name, String description) {
        super(name, description);
    }

    @Override
    public double getDamageMultiplier() {
        return 1.3;
    }

    public void applyRecoil(Pokemon owner) {
        owner.setHp(owner.getHp() - 10);
        System.out.println("L'Orbe Vie blesse " + owner.getName());
    }
}