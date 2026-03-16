package org.example.pokemon.model.items;

import org.example.pokemon.model.AbstractItem;
import org.example.pokemon.model.Pokemon;

public class RestesItem extends AbstractItem {
    public RestesItem(String name, String description) {
        super(name, description);
    }

    @Override
    public void onTurnEnd(Pokemon owner) {
        int heal = 10;
        owner.setHp(owner.getHp() + heal);
        System.out.println(owner.getName() + " récupère un peu de PV via ses Restes.");
    }
}