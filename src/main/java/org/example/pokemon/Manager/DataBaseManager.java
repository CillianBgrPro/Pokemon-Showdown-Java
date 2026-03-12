package org.example.pokemon.Manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseManager {
    private static final String DB_URL = "jdbc:mysql://localhost:3307/classicmodels?allowMultiQueries=true";

    private static final String USER = "root";
    private static final String PASS = "";
    private static Connection connection;

    public DataBaseManager(){
        try{
            connection = DriverManager.getConnection(DB_URL,USER,PASS);
        } catch(SQLException e){
            e.printStackTrace();
        }

    }

    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
        }
        return connection;
    }


}
