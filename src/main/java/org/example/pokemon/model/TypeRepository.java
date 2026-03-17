package org.example.pokemon.model;

import org.example.pokemon.Manager.DataBaseManager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public class TypeRepository {
    public Map<String, Type> findAllEffectiveness() {
        Map<String, Type> typesMap = new HashMap<>();
        String query = "SELECT * FROM Effectiveness";

        try (Connection conn = DataBaseManager.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                String attacker = rs.getString("attacker_type");
                String defender = rs.getString("defender_type");
                double multiplier = rs.getDouble("multiplier");

                typesMap.putIfAbsent(attacker, new Type(attacker));

                typesMap.get(attacker).addEfficacity(defender, multiplier);
        }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return typesMap;
    }
}
