package org.example.pokemon.Manager;

import java.sql.*;

public class DataBaseManager {

    private static final String DB_URL = "jdbc:mysql://localhost:8889/Pokemon_Showdown";
    private static final String USER = "root";
    private static final String PASS = "root";

    private static Connection connection;

    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            try {
                connection = DriverManager.getConnection(DB_URL, USER, PASS);
            } catch (SQLException e) {
                System.err.println("Erreur de connexion ou SQL : " + e.getMessage());
            }
        }
        return connection;
    }

    public DataBaseManager() {
        String query = "SELECT * FROM Pokemons " +
                "JOIN Pokemon_Moves ON Pokemons.id = Pokemon_Moves.pokemon_id " +
                "JOIN Moves ON Moves.id = Pokemon_Moves.move_id";

        try (Connection conn = DataBaseManager.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            System.out.println("--- Liste des Pokemons et leurs attaques ---");

            while(rs.next()){
                System.out.println("Pokémon : " + rs.getString("pokemon_name"));
                System.out.println("HP : " + rs.getInt("hp"));
                System.out.println("Attaque : " + rs.getInt("attack"));
                System.out.println("Défense : " + rs.getInt("defense"));
                System.out.println("Atk Spé : " + rs.getInt("sp_attack"));
                System.out.println("Def Spé : " + rs.getInt("sp_defense"));
                System.out.println("Vitesse : " + rs.getInt("speed"));
                System.out.println("Type : " + rs.getString("type1") + (rs.getString("type2") != null ? " / " + rs.getString("type2") : ""));
                System.out.println("Image : " + rs.getString("sprite_path"));

                System.out.println("> Attaque apprise : " + rs.getString("move_name"));
                System.out.println("> Puissance : " + rs.getInt("power"));
                System.out.println("> Catégorie : " + rs.getString("category"));
                System.out.println("-------------------------------------------");
            }

        } catch (SQLException e) {
            System.err.println("Erreur de connexion ou SQL : " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new DataBaseManager();
    }
}