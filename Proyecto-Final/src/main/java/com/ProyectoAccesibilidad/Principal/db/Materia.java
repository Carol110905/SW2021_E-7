package com.ProyectoAccesibilidad.Principal.db;

public class Materia {
    int IdMateria;
    String Nombre;
    int IdProfesor;
    boolean Examen;
    public Materia(int idMateria, String nombre, int idProfesor, boolean examen) {
        IdMateria = idMateria;
        Nombre = nombre;
        IdProfesor = idProfesor;
        Examen = examen;
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
    
    public boolean isExamen() {
        return Examen;
    }
    public void setExamen(boolean examen) {
        Examen = examen;
    }
    @Override
    public String toString() {
        return "Materia [Examen=" + Examen + ", IdMateria=" + IdMateria + ", IdProfesor=" + IdProfesor + ", Nombre="
                + Nombre + "]";
    }
 
    
}
