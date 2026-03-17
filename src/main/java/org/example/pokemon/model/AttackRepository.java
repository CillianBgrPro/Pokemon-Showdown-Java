package org.example.pokemon.model;

import org.example.pokemon.Manager.DataBaseManager;
import org.example.pokemon.model.effects.AbstractEffect;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AttackRepository {
    private final EffectsRepository effectRepository = new EffectsRepository();
    public List<Attack> findMovesByPokemonId(int pokemonId) {
        List<Attack> moves = new ArrayList<>();
        String query = "SELECT m.* FROM Moves m " +
                "JOIN Pokemon_Moves pm ON m.id = pm.move_id " +
                "WHERE pm.pokemon_id = ?";

        try (Connection conn = DataBaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, pokemonId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String moveName = rs.getString("move_name");

                // 2. On récupère l'objet Effet grâce au nom de l'attaque
                AbstractEffect effect = effectRepository.getEffectByMoveName(moveName);
                moves.add(new Attack(
                        rs.getInt("id"),
                        rs.getString("move_name"),
                        rs.getInt("power"),
                        new Type(rs.getString("type_name")),
                        rs.getString("category"),
                        effect
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return moves;
    }
}