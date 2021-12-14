package com.ProyectoAccesibilidad.Principal.db;

import java.io.File;

public class Pregunta {
    String NombreExamen;
    int NoPregunta;
    String Pregunta;
    String FPregunta;
    String TipoPregunta;
    String RespuestaCorrecta;
    String FRespuestaCorrecta;
    String Respuesta1;
    String FRespuesta1;
    String Respuesta2;
    String FRespuesta2;
    String Respuesta3;
    String FRespuesta3;
    String Respuesta4;
    String FRespuesta4;
    public Pregunta(String nombreExamen, int noPregunta, String pregunta, String fPregunta, String tipoPregunta,
            String respuestaCorrecta, String fRespuestaCorrecta) {
        NombreExamen = nombreExamen;
        NoPregunta = noPregunta;
        Pregunta = pregunta;
        FPregunta = fPregunta;
        TipoPregunta = tipoPregunta;
        RespuestaCorrecta = respuestaCorrecta;
        FRespuestaCorrecta = fRespuestaCorrecta;
    }
    public Pregunta(String nombreExamen, int noPregunta, String pregunta, String filePregunta, String tipoPregunta,
            String respuestaCorrecta, String fileRespuestaC, String respuesta1, String fileOpcion1, String respuesta2,
            String fileOpcion2, String respuesta3, String fileOpcion3, String respuesta4, String fileOpcion4) {
        NombreExamen = nombreExamen;
        NoPregunta = noPregunta;
        Pregunta = pregunta;
        FPregunta = filePregunta;
        TipoPregunta = tipoPregunta;
        RespuestaCorrecta = respuestaCorrecta;
        FRespuestaCorrecta = fileRespuestaC;
        Respuesta1 = respuesta1;
        FRespuesta1 = fileOpcion1;
        Respuesta2 = respuesta2;
        FRespuesta2 = fileOpcion2;
        Respuesta3 = respuesta3;
        FRespuesta3 = fileOpcion3;
        Respuesta4 = respuesta4;
        FRespuesta4 = fileOpcion4;
    }
    public String getNombreExamen() {
        return NombreExamen;
    }
    public void setNombreExamen(String nombreExamen) {
        NombreExamen = nombreExamen;
    }
    public int getNoPregunta() {
        return NoPregunta;
    }
    public void setNoPregunta(int noPregunta) {
        NoPregunta = noPregunta;
    }
    public String getPregunta() {
        return Pregunta;
    }
    public void setPregunta(String pregunta) {
        Pregunta = pregunta;
    }
    public String getFPregunta() {
        return FPregunta;
    }
    public void setFPregunta(String fPregunta) {
        FPregunta = fPregunta;
    }
    public String isTipoPregunta() {
        return TipoPregunta;
    }
    public void setTipoPregunta(String tipoPregunta) {
        TipoPregunta = tipoPregunta;
    }
    public String getRespuestaCorrecta() {
        return RespuestaCorrecta;
    }
    public void setRespuestaCorrecta(String respuestaCorrecta) {
        RespuestaCorrecta = respuestaCorrecta;
    }
    public String getFRespuestaCorrecta() {
        return FRespuestaCorrecta;
    }
    public void setFRespuestaCorrecta(String fRespuestaCorrecta) {
        FRespuestaCorrecta = fRespuestaCorrecta;
    }
    public String getRespuesta1() {
        return Respuesta1;
    }
    public void setRespuesta1(String respuesta1) {
        Respuesta1 = respuesta1;
    }
    public String getFRespuesta1() {
        return FRespuesta1;
    }
    public void setFRespuesta1(String fRespuesta1) {
        FRespuesta1 = fRespuesta1;
    }
    public String getRespuesta2() {
        return Respuesta2;
    }
    public void setRespuesta2(String respuesta2) {
        Respuesta2 = respuesta2;
    }
    public String getFRespuesta2() {
        return FRespuesta2;
    }
    public void setFRespuesta2(String fRespuesta2) {
        FRespuesta2 = fRespuesta2;
    }
    public String getRespuesta3() {
        return Respuesta3;
    }
    public void setRespuesta3(String respuesta3) {
        Respuesta3 = respuesta3;
    }
    public String getFRespuesta3() {
        return FRespuesta3;
    }
    public void setFRespuesta3(String fRespuesta3) {
        FRespuesta3 = fRespuesta3;
    }
    public String getRespuesta4() {
        return Respuesta4;
    }
    public void setRespuesta4(String respuesta4) {
        Respuesta4 = respuesta4;
    }
    public String getFRespuesta4() {
        return FRespuesta4;
    }
    public void setFRespuesta4(String fRespuesta4) {
        FRespuesta4 = fRespuesta4;
    }
    @Override
    public String toString() {
        return "Pregunta [FPregunta=" + FPregunta + ", FRespuesta1=" + FRespuesta1 + ", FRespuesta2=" + FRespuesta2
                + ", FRespuesta3=" + FRespuesta3 + ", FRespuesta4=" + FRespuesta4 + ", FRespuestaCorrecta="
                + FRespuestaCorrecta + ", NoPregunta=" + NoPregunta + ", NombreExamen=" + NombreExamen + ", Pregunta="
                + Pregunta + ", Respuesta1=" + Respuesta1 + ", Respuesta2=" + Respuesta2 + ", Respuesta3=" + Respuesta3
                + ", Respuesta4=" + Respuesta4 + ", RespuestaCorrecta=" + RespuestaCorrecta + ", TipoPregunta="
                + TipoPregunta + "]";
    }

    
   
}
