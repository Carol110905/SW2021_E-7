package com.ProyectoAccesibilidad.Principal.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    private String url = "jdbc:mysql://localhost/proyectofinal";
    private String username = "root";
    private String password = "";
    private String driverName = "com.mysql.jdbc.Driver";
    private Connection conn = null;

    public Connection getConnection() {
        try {
            Class.forName(driverName);
            conn = DriverManager.getConnection(url, username, password);
            System.out.println(conn);
        } catch (SQLException e) {
            System.out.println(e);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        
        return conn;
    }
}
