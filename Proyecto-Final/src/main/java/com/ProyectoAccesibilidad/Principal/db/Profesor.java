package com.ProyectoAccesibilidad.Principal.db;

public class Profesor {
    private String ID;
    private String Username;
    private String Password;
    private String Nombre;

    Profesor(String id, String User, String Pass, String name){
        this.ID = id;
        this.Username = User;
        this.Password = Pass;
        this.Nombre = name;
    }

    public String getID() {
        return ID;
    }

    public void setID(String iD) {
        ID = iD;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    @Override
    public String toString() {
        return "Profesor [ID=" + ID + ", Nombre=" + Nombre + ", Password=" + Password + ", Username=" + Username + "]";
    }
    
}
