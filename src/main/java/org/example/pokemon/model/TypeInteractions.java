package org.example.pokemon.model;

import java.util.HashMap;
import java.util.Map;

public class TypeInteractions {
    private final Map<String, Double> coefficients = new HashMap<>();

    public void setEffectiveness(String typeName, double multiplier) {
        coefficients.put(typeName, multiplier);
    }

    public double getMultiplier(String opponentTypeName) {
        return coefficients.getOrDefault(opponentTypeName, 1.0);
    }
}
