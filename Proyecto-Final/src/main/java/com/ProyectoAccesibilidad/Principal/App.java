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
            int NoPreguntas = Integer
                    .parseInt(convertInputStreamToString(req.raw().getPart("NoPreguntas").getInputStream()));
            System.out.println(NoPreguntas);
            for (int i = 0; i < NoPreguntas; i++) {
                String Pregunta = convertInputStreamToString(req.raw().getPart("Pregunta" + i).getInputStream());
                System.out.println(Pregunta);

                req.attribute("org.eclipse.jetty.multipartConfig", new MultipartConfigElement("/temp"));
                Part uf = req.raw().getPart("FilePregunta" + i);
                System.out.println(uf);

                File uploadDir = new File("upload");
                uploadDir.mkdir();

                Path tempFile = Files.createTempFile(uploadDir.toPath(), "", "");
                req.attribute("org.eclipse.jetty.multipartConfig", new MultipartConfigElement("/temp"));
                try (InputStream input = req.raw().getPart("FilePregunta" + i).getInputStream()) {
                    Files.copy(input, tempFile, StandardCopyOption.REPLACE_EXISTING);
                }
                logInfo(req, tempFile, uf );

                /*
                 * if (req.raw().getAttribute("org.eclipse.jetty.multipartConfig") == null) {
                 * MultipartConfigElement multipartConfigElement = new
                 * MultipartConfigElement(System.getProperty("java.io.tmpdir"));
                 * req.raw().setAttribute("org.eclipse.jetty.multipartConfig",
                 * multipartConfigElement);
                 * }
                 * Part file = req.raw().getPart("File");
                 * System.out.println(file);
                 * Part name = req.raw().getPart("Formulario");
                 * System.out.println(name);
                 * return null;
                 * String filename = file.getName();
                 * if(name.getSize() > 0){
                 * try{
                 * filename = IOUtils.toString(name.getInputStream(), StandardCharsets.UTF_8);
                 * } catch(Exception e){
                 * e.printStackTrace();
                 * }
                 * }
                 * Path filePath = Paths.get(".",filename);
                 * Files.copy(file.getInputStream(),filePath);
                 * return "Done!";
                 * });
                 * FileWriter fichero = null;
                 * 
                 * try {
                 * System.out.println(linea);
                 * fichero = new FileWriter("fichero_escritura.txt");
                 * fichero.write(linea + "\n");
                 * fichero.close();
                 * System.out.println(linea);
                 * } catch (Exception ex) {
                 * System.out.println("Mensaje de la excepción: " + ex.getMessage());
                 * }
                 * Path tempFile = Files.createTempFile(uploadDir.toPath(), "", "");
                 * 
                 * req.attribute("org.eclipse.jetty.multipartConfig", new
                 * MultipartConfigElement("/temp"));
                 * 
                 * try (InputStream input = req.raw().getPart("videoGrabado").getInputStream())
                 * { // getPart needs to use same "name" as input field in form
                 * Files.copy(input, tempFile, StandardCopyOption.REPLACE_EXISTING);
                 * }
                 * 
                 * logInfo(req, tempFile);
                 * return "<h1>You uploaded this image:<h1><img src='" + tempFile.getFileName()
                 * + "'>";
                 */

            }
            return null;
        });

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
