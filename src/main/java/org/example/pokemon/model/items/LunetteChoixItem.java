package org.example.pokemon.model.items;

import org.example.pokemon.model.AbstractItem;

public class LunetteChoixItem extends AbstractItem {
    public LunetteChoixItem(String name, String description) {
        super(name, description);
    }

    public double getSpecialBoost() {
        return 1.5;
    }
}