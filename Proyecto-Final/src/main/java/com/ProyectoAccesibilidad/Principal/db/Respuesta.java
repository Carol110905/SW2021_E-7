package com.ProyectoAccesibilidad.Principal.db;

public class Respuesta {
    String NombreMateria;
    String NombreAlumno;
    String Pregunta;
    String RespuestaCorrecta;
    int NoPregunta;
    String TipoPregunta;
    String Respuesta;
    String FRespuesta;
    public Respuesta(String nombreMateria, String nombreAlumno, int noPregunta, String tipoPregunta, String respuesta) {
        NombreMateria = nombreMateria;
        NombreAlumno = nombreAlumno;
        NoPregunta = noPregunta;
        TipoPregunta = tipoPregunta;
        Respuesta = respuesta;
    }
    public Respuesta(String nombreMateria, String nombreAlumno, int noPregunta, String tipoPregunta, String respuesta, String fRespuesta) {
        NombreMateria = nombreMateria;
        NombreAlumno = nombreAlumno;
        NoPregunta = noPregunta;
        TipoPregunta = tipoPregunta;
        Respuesta = respuesta;
        FRespuesta = fRespuesta;
    }
    
    public String getRespuestaCorrecta() {
        return RespuestaCorrecta;
    }
    public void setRespuestaCorrecta(String respuestaCorrecta) {
        RespuestaCorrecta = respuestaCorrecta;
    }
    public String getPregunta() {
        return Pregunta;
    }
    public void setPregunta(String pregunta) {
        Pregunta = pregunta;
    }
    public String getNombreMateria() {
        return NombreMateria;
    }
    public void setNombreMateria(String nombreMateria) {
        NombreMateria = nombreMateria;
    }
    public String getNombreAlumno() {
        return NombreAlumno;
    }
    public void setNombreAlumno(String nombreAlumno) {
        NombreAlumno = nombreAlumno;
    }
    public int getNoPregunta() {
        return NoPregunta;
    }
    public void setNoPregunta(int noPregunta) {
        NoPregunta = noPregunta;
    }
    public String getTipoPregunta() {
        return TipoPregunta;
    }
    public void setTipoPregunta(String tipoPregunta) {
        TipoPregunta = tipoPregunta;
    }
    public String getRespuesta() {
        return Respuesta;
    }
    public void setRespuesta(String respuesta) {
        Respuesta = respuesta;
    }
    public String getFRespuesta() {
        return FRespuesta;
    }
    public void setFRespuesta(String fRespuesta) {
        FRespuesta = fRespuesta;
    }
    @Override
    public String toString() {
        return "Respuesta [FRespuesta=" + FRespuesta + ", NoPregunta=" + NoPregunta + ", NombreAlumno=" + NombreAlumno
                + ", NombreMateria=" + NombreMateria + ", Pregunta=" + Pregunta + ", Respuesta=" + Respuesta
                + ", RespuestaCorrecta=" + RespuestaCorrecta + ", TipoPregunta=" + TipoPregunta + "]";
    }
   

    
}
