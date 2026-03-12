package org.example.pokemon.Mapper;

import org.example.pokemon.model.Type;
import org.example.pokemon.Manager.DataBaseManager;
import java.sql.*;

// ça vient de Gemini je m'occupe de ça demain matin
public class TypeMapper {

    public static Type map(ResultSet rs) throws SQLException {
        Type t = new Type(rs.getString("nom"));
        chargerInteractions(t);
        return t;
    }

    private static void chargerInteractions(Type t) {
        String sql = "SELECT type_defenseur, multiplicateur FROM type_interactions WHERE type_attaquant = ?";

        try (Connection c = DataBaseManager.getConnection();
             PreparedStatement pst = c.prepareStatement(sql)) {

            pst.setString(1, t.getNom());
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                t.ajouterEfficacite(
                        rs.getString("type_defenseur"),
                        rs.getDouble("multiplicateur")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}