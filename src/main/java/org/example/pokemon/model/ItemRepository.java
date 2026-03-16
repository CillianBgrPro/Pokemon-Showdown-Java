package org.example.pokemon.model;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.example.pokemon.Manager.DataBaseManager;
import org.example.pokemon.logic.ItemFactory;

public class ItemRepository {
    public List<AbstractItem> findAll() {
        List<AbstractItem> items = new ArrayList<>();
        String sql = "SELECT * FROM Items";

        try (Connection conn = DataBaseManager.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                AbstractItem item = ItemFactory.createItem(
                        rs.getString("item_name"),
                        rs.getString("description")
                );
                items.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    }
}