package com.desafiocrea.connection;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    private static Connection con = null;
    private static String url = "jdbc:postgresql://127.0.0.1:5432/desafio_crea";
    private static String username = "postgres";
    private static String password = "canelad0";
    public static Connection getDatabaseConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }
}
