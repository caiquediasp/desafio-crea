package com.desafiocrea.connection;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    private static Connection con = null;
    private static String url = ;
    private static String username = ;
    private static String password = ;
    public static Connection getDatabaseConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }
}
