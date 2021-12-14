package com.ProyectoAccesibilidad.Principal;

import static spark.Spark.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.google.gson.*;

import spark.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.net.FileNameMap;
import java.nio.file.*;
import static spark.Spark.*;
import static spark.debug.DebugScreen.*;

import spark.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import static spark.Spark.*;
import static spark.debug.DebugScreen.*;

import com.ProyectoAccesibilidad.Principal.db.Alumno;
import com.ProyectoAccesibilidad.Principal.db.AlumnoDAO;
import com.ProyectoAccesibilidad.Principal.db.Materia;
import com.ProyectoAccesibilidad.Principal.db.MateriaDAO;
import com.ProyectoAccesibilidad.Principal.db.Profesor;
import com.ProyectoAccesibilidad.Principal.db.ProfesorDAO;

import org.apache.commons.io.IOUtils;
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
                if (A != null) {
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

        get("/Formulario", (rq, rs) -> {
            Map<String, Object> variables = new HashMap<>();
            IContext context = new Context(rq.raw().getLocale(), variables);
            String out = ThymeleafUtil.getTemplateEngine().process("2103_central/CrearExamen", context);
            return out;
        });

        get("/crearExamen", (rq, rs) -> {
            String m = rq.queryParams("materia");
            Map<String, Object> variables = new HashMap<>();
            variables.put("Nombre", m);

            IContext context = new Context(rq.raw().getLocale(), variables);
            String out = ThymeleafUtil.getTemplateEngine().process("2103_central/CrearExamen", context);
            return out;
        });

        get("/GuardarExamen", (rq, rs) -> {

            return null;
        });

        post("/Escribirtxt", (req, res) -> {
            req.attribute("org.eclipse.jetty.multipartConfig", new MultipartConfigElement("/temp"));
            int NoPreguntas = Integer.parseInt(convertInputStreamToString(req.raw().getPart("NoPreguntas").getInputStream()));
            String nombre = convertInputStreamToString(req.raw().getPart("Examen").getInputStream());
            System.out.println("Examen: " + nombre + " con :" + NoPreguntas + " preguntas");

            for (int i = 0; i < NoPreguntas; i++) {
                String Pregunta = convertInputStreamToString(req.raw().getPart("Pregunta" + i).getInputStream());
                String fileName = "FilePregunta" + i;
                guardarVideo(req, fileName, nombre);

                String Tipo = convertInputStreamToString(req.raw().getPart("Tipo" + i).getInputStream());
                if (Tipo.equals("Abierta")) {
                    String RespuestaC = convertInputStreamToString(req.raw().getPart("RespuestaCorrecta-" + i).getInputStream());
                    fileName = "FRespuestaC" + i;
                    guardarVideo(req, fileName, nombre);
                }
                if (Tipo.equals("Cerrada")) {
                    String RespuestaC = convertInputStreamToString(req.raw().getPart("RespuestaCorrecta-" + i).getInputStream());
                    fileName = "FRespuestaC" + i;
                    guardarVideo(req, fileName, nombre);
                    String Opcion1 = convertInputStreamToString(req.raw().getPart("Opcion1-" + i).getInputStream());
                    fileName = "FOpcion1-" + i;
                    guardarVideo(req, fileName, nombre);
                    String Opcion2 = convertInputStreamToString(req.raw().getPart("Opcion2-" + i).getInputStream());
                    fileName = "FOpcion2-" + i;
                    guardarVideo(req, fileName, nombre);
                    String Opcion3 = convertInputStreamToString(req.raw().getPart("Opcion3-" + i).getInputStream());
                    fileName = "FOpcion3-" + i;
                    guardarVideo(req, fileName, nombre);
                    String Opcion4 = convertInputStreamToString(req.raw().getPart("Opcion4-" + i).getInputStream());
                    fileName = "FOpcion4-" + i;
                    guardarVideo(req, fileName, nombre);
                }

            }
            return null;
        });

    }

    private static void guardarVideo(Request req, String fileName, String materia) {
        try {
            req.attribute("org.eclipse.jetty.multipartConfig", new MultipartConfigElement("/temp"));
            Part uf = req.raw().getPart(fileName);
            System.out.println(uf);

            File uploadDir = new File("upload");
            uploadDir.mkdir();
            String nombredoc = materia + " " + uf.getName();
            Path tempFile = Files.createTempFile(uploadDir.toPath(), nombredoc, ".mp4");
            req.attribute("org.eclipse.jetty.multipartConfig", new MultipartConfigElement("/temp"));
            try (InputStream input = req.raw().getPart(fileName).getInputStream()) {
                Files.copy(input, tempFile, StandardCopyOption.REPLACE_EXISTING);
            }
        } catch (IOException | ServletException e) {

        }
    }

    private static void logInfo(Request req, Path tempFile, Part part) throws IOException, ServletException {
        System.out.println("Uploaded file '" + getFileName(part) + "' saved as '"
                + tempFile.toAbsolutePath() + "'");
    }

    private static String getFileName(Part part) {
        for (String cd : part.getHeader("content-disposition").split(";")) {
            if (cd.trim().startsWith("filename")) {
                return cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }

    private static String convertInputStreamToString(InputStream is) throws IOException {
        return IOUtils.toString(is, StandardCharsets.UTF_8);
    }
}
