package org.example.pokemon.model.items;

import org.example.pokemon.model.AbstractItem;
import org.example.pokemon.model.Pokemon;

public class BaieSitrusItem extends AbstractItem {
    private boolean consumed = false;

    public BaieSitrusItem(String name, String description) {
        super(name, description);
    }

    @Override
    public void onTakeDamage(Pokemon owner, int damage) {
        if (!consumed && owner.getHp() < 50) {
            owner.setHp(owner.getHp() + 30);
            consumed = true;
            System.out.println(owner.getName() + " consomme sa Baie Sitrus et se soigne.");
        }
    }
}