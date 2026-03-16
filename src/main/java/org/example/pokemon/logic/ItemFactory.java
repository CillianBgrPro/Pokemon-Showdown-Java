package org.example.pokemon.logic;

import org.example.pokemon.model.AbstractItem;
import org.example.pokemon.model.items.*;

public class ItemFactory {
    public static AbstractItem createItem(String itemName, String description) {
        if (itemName == null) return null;

        switch (itemName) {
            case "Restes":
                return new RestesItem(itemName, description);
            case "Orbe Vie":
                return new OrbeVieItem(itemName, description);
            case "Lunettes Choix":
                return  new LunetteChoixItem(itemName, description);
            case "Ceinture Force":
                return new CeintureForceItem(itemName, description);
            case "Baie Sitrus":
                return new BaieSitrusItem(itemName, description);
            default:
                return null;
        }
    }
}