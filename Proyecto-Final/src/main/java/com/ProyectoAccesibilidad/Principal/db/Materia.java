package com.ProyectoAccesibilidad.Principal.db;

public class Materia {
    int IdMateria;
    String Nombre;
    int IdProfesor;
    public Materia(int idMateria, String nombre, int idProfesor) {
        IdMateria = idMateria;
        Nombre = nombre;
        IdProfesor = idProfesor;
    }
    public int getIdMateria() {
        return IdMateria;
    }
    public void setIdMateria(int idMateria) {
        IdMateria = idMateria;
    }
    public String getNombre() {
        return Nombre;
    }
    public void setNombre(String nombre) {
        Nombre = nombre;
    }
    public int getIdProfesor() {
        return IdProfesor;
    }
    public void setIdProfesor(int idProfesor) {
        IdProfesor = idProfesor;
    }
    @Override
    public String toString() {
        return "Materia [IdMateria=" + IdMateria + ", IdProfesor=" + IdProfesor + ", Nombre=" + Nombre + "]";
    }
    
}
