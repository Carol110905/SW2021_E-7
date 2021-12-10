package com.ProyectoAccesibilidad.Principal;

import static spark.Spark.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.google.gson.*;



import com.ProyectoAccesibilidad.Principal.db.Alumno;
import com.ProyectoAccesibilidad.Principal.db.AlumnoDAO;
import com.ProyectoAccesibilidad.Principal.db.Materia;
import com.ProyectoAccesibilidad.Principal.db.MateriaDAO;
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
    private static Gson gson = new Gson();
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
            String password = rq.queryParams("password");
            p = pd.BuscarProfesor(usuario);
            if (p == null) {
                A = ad.BuscarAlumno(usuario);
                if(A != null){
                    if (password.equals(String.valueOf(A.getPassword()))) {
                        MateriaDAO md = new MateriaDAO();
                        List<Materia> materias = new ArrayList<Materia>();
                        Map<String, Object> variables = new HashMap<>();
                        materias = md.BuscarMateriaAlumno(Integer.parseInt(A.getID()));
                        variables.put("Tipo", false);
                        variables.put("Rol", "Alumno");
                        variables.put("Nombre", A.getNombre());
                        variables.put("listaMaterias", materias);
                        IContext context = new Context(rq.raw().getLocale(), variables);
                        String out = ThymeleafUtil.getTemplateEngine().process("2103_central/index", context);
                        return out;
                    } else {
                    }
                }
            } else {
                if (p != null) {
                    if (password.equals(String.valueOf(p.getPassword()))) {
                        MateriaDAO md = new MateriaDAO();
                        List<Materia> materias = new ArrayList<Materia>();
                        Map<String, Object> variables = new HashMap<>();
                        materias = md.BuscarMateriaProfesor(Integer.parseInt(p.getID()));
                        variables.put("Tipo", true);
                        variables.put("Rol", "Profesor");
                        variables.put("Nombre", p.getNombre());
                        variables.put("listaMaterias", materias);
                        IContext context = new Context(rq.raw().getLocale(), variables);
                        String out = ThymeleafUtil.getTemplateEngine().process("2103_central/index", context);
                        return out;
                    } else {
                    }

                }
            }
            return null;
        });

        get("/Formulario", (rq, rs) ->{
            Map<String, Object> variables = new HashMap<>();
            IContext context = new Context(rq.raw().getLocale(), variables);
            String out = ThymeleafUtil.getTemplateEngine().process("2103_central/CrearExamen", context);
            return out;
        });

        get("/crearExamen", (rq, rs) ->{
            String m = rq.queryParams("materia");
            Map<String, Object> variables = new HashMap<>();
            variables.put("Nombre", m);
            
            IContext context = new Context(rq.raw().getLocale(), variables);
            String out = ThymeleafUtil.getTemplateEngine().process("2103_central/CrearExamen", context);
            return out;
        });

        get("/GuardarExamen", (rq,rs)->{
            
            return null;
        });
    }


    /*public Materia buscarMaterias(Profesor p){

    }*/
}


