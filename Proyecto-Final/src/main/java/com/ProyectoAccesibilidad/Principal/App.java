package com.ProyectoAccesibilidad.Principal;

import static spark.Spark.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ProyectoAccesibilidad.Principal.db.Alumno;
import com.ProyectoAccesibilidad.Principal.db.AlumnoDAO;
import com.ProyectoAccesibilidad.Principal.db.Profesor;
import com.ProyectoAccesibilidad.Principal.db.ProfesorDAO;

import org.thymeleaf.context.Context;
import org.thymeleaf.context.IContext;

//import spark.ModelAndView;
/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        port(1234);
        staticFiles.location("/");

        get("/", (rq, rs) -> {
            rs.redirect("/login.html");
            return null;
        });

        get("/validar", (rq, rs) -> {
            ProfesorDAO pd = new ProfesorDAO();
            AlumnoDAO ad = new AlumnoDAO();
            Profesor p;
            Alumno A;
            String usuario = rq.queryParams("usuario");
            System.out.println(usuario);
            String password = rq.queryParams("password");
            p = pd.BuscarProfesor(usuario);
            if (p == null) {
                A = ad.BuscarAlumno(usuario);
                if(A != null){
                    if (password.equals(String.valueOf(A.getPassword()))) {
                        System.out.println("Si llegue");
                        Map<String, Object> variables = new HashMap<>();
                        IContext context = new Context(rq.raw().getLocale(), variables);
                        String out = ThymeleafUtil.getTemplateEngine().process("Hola", context);
                        System.out.println("Existe el Alumno");
                        return out;
                    } else {
                        System.out.println("No existe el Alumno");
                    }
                }
            } else {
                if (p != null) {
                    if (password.equals(String.valueOf(p.getPassword()))) {
                        System.out.println("Si llegue");
                        Map<String, Object> variables = new HashMap<>();
                        IContext context = new Context(rq.raw().getLocale(), variables);
                        String out = ThymeleafUtil.getTemplateEngine().process("chillaid/index", context);
                        System.out.println("Existe el Profesor");
                        return out;
                    } else {
                        System.out.println("No existe el Profesor");
                    }

                }
            }
            return null;
        });
    }
}
