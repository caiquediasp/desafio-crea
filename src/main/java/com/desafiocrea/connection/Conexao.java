package com.desafiocrea.connection;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    private static Connection con = null;
    private static String url = "jdbc:postgresql://api-desafio-crea.cxu2iwoee8kt.us-east-2.rds.amazonaws.com:5432/postgres";
    private static String username = "postgres";
    private static String password = "canelado";
    public static Connection getDatabaseConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }
}
