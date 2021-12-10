package com.ProyectoAccesibilidad.Principal.db;

public class Pregunta {
    int NoPregunta;
    boolean TipoPregunta;
    String RespuestaCorrecta;

    String Respuesta1;
    String Respuesta2;
    String Respuesta3;
    String Respuesta4;

    public Pregunta(int noPregunta, boolean tipoPregunta, String respuestaCorrecta, String respuesta1,
            String respuesta2, String respuesta3, String respuesta4) {
        NoPregunta = noPregunta;
        TipoPregunta = tipoPregunta;
        RespuestaCorrecta = respuestaCorrecta;
        Respuesta1 = respuesta1;
        Respuesta2 = respuesta2;
        Respuesta3 = respuesta3;
        Respuesta4 = respuesta4;
    }

    public Pregunta(int noPregunta, boolean tipoPregunta, String respuestaCorrecta, String respuesta1) {
        NoPregunta = noPregunta;
        TipoPregunta = tipoPregunta;
        RespuestaCorrecta = respuestaCorrecta;
        Respuesta1 = respuesta1;
    }

    public int getNoPregunta() {
        return NoPregunta;
    }

    public void setNoPregunta(int noPregunta) {
        NoPregunta = noPregunta;
    }

    public boolean isTipoPregunta() {
        return TipoPregunta;
    }

    public void setTipoPregunta(boolean tipoPregunta) {
        TipoPregunta = tipoPregunta;
    }

    public String getRespuestaCorrecta() {
        return RespuestaCorrecta;
    }

    public void setRespuestaCorrecta(String respuestaCorrecta) {
        RespuestaCorrecta = respuestaCorrecta;
    }

    public String getRespuesta1() {
        return Respuesta1;
    }

    public void setRespuesta1(String respuesta1) {
        Respuesta1 = respuesta1;
    }

    public String getRespuesta2() {
        return Respuesta2;
    }

    public void setRespuesta2(String respuesta2) {
        Respuesta2 = respuesta2;
    }

    public String getRespuesta3() {
        return Respuesta3;
    }

    public void setRespuesta3(String respuesta3) {
        Respuesta3 = respuesta3;
    }

    public String getRespuesta4() {
        return Respuesta4;
    }

    public void setRespuesta4(String respuesta4) {
        Respuesta4 = respuesta4;
    }

    
    
}
