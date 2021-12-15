package com.ProyectoAccesibilidad.Principal.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    private String url = "jdbc:mysql://localhost/paccesjoaquin";
    private String username = "joaquinpf";
    private String password = "123456789";
    private String driverName = "com.mysql.jdbc.Driver";
    private Connection conn = null;

    public Connection getConnection() {
        try {
            Class.forName(driverName);
            conn = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            System.out.println(e);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        
        return conn;
    }
}
