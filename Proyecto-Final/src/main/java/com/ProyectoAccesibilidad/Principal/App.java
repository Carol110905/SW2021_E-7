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
import javax.swing.text.Document;

import java.io.*;
import java.net.FileNameMap;
import java.nio.file.*;
import static spark.debug.DebugScreen.*;
import java.nio.charset.StandardCharsets;

import com.ProyectoAccesibilidad.Principal.db.*;

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
    private static ArrayList<Pregunta> preguntas = new ArrayList<Pregunta>();
    private static ArrayList<Respuesta> respuestas = new ArrayList<Respuesta>();

    public static void main(String[] args) {
        MateriaDAO materiasd = new MateriaDAO();
        materiasd.reiniciarExamen();
        port(getHerokuAssignedPort());
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

        get("/iniciarExamen", (rq, rs) -> {
            String nombreMateria = rq.queryParams("materia");
            String nombreAlumno = rq.queryParams("Alumno");
            List<Pregunta> preguntasExamen = new ArrayList<Pregunta>();
            for (int i = 0; i < preguntas.size(); i++) {
                if (nombreMateria.equals(preguntas.get(i).getNombreExamen())) {
                    preguntasExamen.add(preguntas.get(i));
                }

            }
            Map<String, Object> variables = new HashMap<>();
            variables.put("NombreAlumno", nombreAlumno);
            variables.put("Nombre", preguntasExamen.get(0).getNombreExamen());
            variables.put("NoPreguntas", preguntas.size());
            variables.put("preguntas", preguntasExamen);
            IContext context = new Context(rq.raw().getLocale(), variables);
            String out = ThymeleafUtil.getTemplateEngine().process("2103_central/ContestarExamen", context);

            return out;
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
        get("/verRespuestas", (rq, rs) -> {
            String m = rq.queryParams("materia");
            MateriaDAO mad = new MateriaDAO();
            AlumnoDAO ald = new AlumnoDAO();
            int idMateria = mad.BuscarIdMateria(m);
            ArrayList<Integer> idAlumnos = mad.BuscarIDAlumnoMateria(idMateria);
            ArrayList<String> nombres = new ArrayList<String>();
            ArrayList<Respuesta> respuestasEnviar = new ArrayList<Respuesta>();
            for (int i = 0; i < idAlumnos.size(); i++) {
                nombres.add(ald.BuscarNombreAlumno(idAlumnos.get(i)));
            }
            for (int i = 0; i < respuestas.size(); i++) {
                if (respuestas.get(i).getNombreMateria().equals(m)) {
                    respuestasEnviar.add(respuestas.get(i));
                }
                for (int j = 0; j < preguntas.size(); j++) {

                    if (preguntas.get(j).getNombreExamen().equals(m)) {
                        if (respuestasEnviar.get(i).getNoPregunta() == preguntas.get(j).getNoPregunta()) {
                            respuestasEnviar.get(i).setPregunta(preguntas.get(j).getPregunta());
                            respuestasEnviar.get(i).setRespuestaCorrecta(preguntas.get(j).getRespuestaCorrecta());
                        }
                    }
                }

            }
            Map<String, Object> variables = new HashMap<>();
            variables.put("Respuestas", respuestasEnviar);

            IContext context = new Context(rq.raw().getLocale(), variables);
            String out = ThymeleafUtil.getTemplateEngine().process("2103_central/VerRespuestas", context);
            return out;
        });

        get("/GuardarExamen", (rq, rs) -> {

            return null;
        });

        post("/Escribirtxt", (req, res) -> {

            req.attribute("org.eclipse.jetty.multipartConfig", new MultipartConfigElement("/temp"));
            int NoPreguntas = Integer
                    .parseInt(convertInputStreamToString(req.raw().getPart("NoPreguntas").getInputStream()));
            String nombre = convertInputStreamToString(req.raw().getPart("Examen").getInputStream());

            for (int i = 0; i < NoPreguntas; i++) {
                String Pregunta = convertInputStreamToString(req.raw().getPart("Pregunta" + i).getInputStream());
                String fileName = "FilePregunta" + i;
                String filePregunta = guardarVideo(req, fileName, nombre);

                String Tipo = convertInputStreamToString(req.raw().getPart("Tipo" + i).getInputStream());
                if (Tipo.equals("Abierta")) {
                    String RespuestaC = convertInputStreamToString(
                            req.raw().getPart("RespuestaCorrecta-" + i).getInputStream());
                    fileName = "FRespuestaC" + i;
                    String fileRespuestaC = guardarVideo(req, fileName, nombre);

                    preguntas.add(
                            new Pregunta(nombre, i, Pregunta, filePregunta, "Abierta", RespuestaC, fileRespuestaC));
                }
                if (Tipo.equals("Cerrada")) {
                    String RespuestaC = convertInputStreamToString(
                            req.raw().getPart("RespuestaCorrecta-" + i).getInputStream());
                    fileName = "FRespuestaC" + i;
                    String fileRespuestaC = guardarVideo(req, fileName, nombre);
                    String Opcion1 = convertInputStreamToString(req.raw().getPart("Opcion1-" + i).getInputStream());
                    fileName = "FOpcion1-" + i;
                    String fileOpcion1 = guardarVideo(req, fileName, nombre);
                    String Opcion2 = convertInputStreamToString(req.raw().getPart("Opcion2-" + i).getInputStream());
                    fileName = "FOpcion2-" + i;
                    String fileOpcion2 = guardarVideo(req, fileName, nombre);
                    String Opcion3 = convertInputStreamToString(req.raw().getPart("Opcion3-" + i).getInputStream());
                    fileName = "FOpcion3-" + i;
                    String fileOpcion3 = guardarVideo(req, fileName, nombre);
                    String Opcion4 = convertInputStreamToString(req.raw().getPart("Opcion4-" + i).getInputStream());
                    fileName = "FOpcion4-" + i;
                    String fileOpcion4 = guardarVideo(req, fileName, nombre);

                    preguntas.add(new Pregunta(nombre, i, Pregunta, filePregunta, "Cerrada", RespuestaC, fileRespuestaC,
                            Opcion1, fileOpcion1,
                            Opcion2, fileOpcion2, Opcion3, fileOpcion3, Opcion4, fileOpcion4));
                }

            }
            MateriaDAO m = new MateriaDAO();
            m.examenCreado(nombre);
            return "Listo";
        });
        post("/guardarRespuestas", (req, res) -> {
            req.attribute("org.eclipse.jetty.multipartConfig", new MultipartConfigElement("/temp"));
            int NoPreguntas = Integer
                    .parseInt(convertInputStreamToString(req.raw().getPart("NoPreguntas").getInputStream()));
            String nombreExamen = convertInputStreamToString(req.raw().getPart("NombreExamen").getInputStream());
            String nombreAlumno = convertInputStreamToString(req.raw().getPart("NombreAlumno").getInputStream());
            String nombreExamenes = convertInputStreamToString(req.raw().getPart("NombreExamen").getInputStream());
            nombreExamen += " " + nombreAlumno;
            for (int i = 0; i < NoPreguntas; i++) {
                String TipoPregunta = convertInputStreamToString(req.raw().getPart("Tipo" + i).getInputStream());
                if (TipoPregunta.equals("Abierta")) {
                    String RespuestaPregunta = convertInputStreamToString(
                            req.raw().getPart("RespuestaPregunta-" + i).getInputStream());
                    String fileName = "FRespuestaPregunta" + i;
                    String fileOpcion = guardarVideo(req, fileName, nombreExamen);
                    respuestas.add(
                            new Respuesta(nombreExamenes, nombreAlumno, i, TipoPregunta, RespuestaPregunta,
                                    fileOpcion));
                }
                if (TipoPregunta.equals("Cerrada")) {
                    String RespuestaPregunta = convertInputStreamToString(
                            req.raw().getPart("RespuestaPregunta-" + i).getInputStream());
                    respuestas.add(new Respuesta(nombreExamenes, nombreAlumno, i, TipoPregunta, RespuestaPregunta));

                }
            }

            return "Listo";
        });
        post("/guardarGrabacion", (req, res) -> {

            File uploadDir = new File("upload");
            uploadDir.mkdir(); // create the upload directory if it doesn't exist

            staticFiles.externalLocation("upload");
            Path tempFile = Files.createTempFile(uploadDir.toPath(), "", "");

            req.attribute("org.eclipse.jetty.multipartConfig", new MultipartConfigElement("/temp"));

            try (InputStream input = req.raw().getPart("videoGrabado").getInputStream()) { // getPart needs to use same
                                                                                           // "name" as input field in
                                                                                           // form
                Files.copy(input, tempFile, StandardCopyOption.REPLACE_EXISTING);
            }

            logInfo2(req, tempFile);
            return "<h1>You uploaded this image:<h1><img src='" + tempFile.getFileName() + "'>";

        });

    }

    private static void logInfo2(Request req, Path tempFile) throws IOException, ServletException {
        System.out.println("Uploaded file '" + getFileName2(req.raw().getPart("uploaded_file")) + "' saved as '"
                + tempFile.toAbsolutePath() + "'");
    }

    private static String getFileName2(Part part) {
        for (String cd : part.getHeader("content-disposition").split(";")) {
            if (cd.trim().startsWith("filename")) {
                return cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }

    private static String guardarVideo(Request req, String fileName, String materia) {
        try {
            req.attribute("org.eclipse.jetty.multipartConfig", new MultipartConfigElement("/temp"));
            Part uf = req.raw().getPart(fileName);

            File uploadDir = new File("upload");
            uploadDir.mkdir();
            String nombredoc = materia + " " + uf.getName();
            Path tempFile = Files.createTempFile(uploadDir.toPath(), nombredoc, ".mp4");
            req.attribute("org.eclipse.jetty.multipartConfig", new MultipartConfigElement("/temp"));
            try (InputStream input = req.raw().getPart(fileName).getInputStream()) {
                Files.copy(input, tempFile, StandardCopyOption.REPLACE_EXISTING);
            }

            File file = logInfo(req, tempFile, uf);
            File fileRename = new File("upload/" + nombredoc
                            + ".mp4");
            file.renameTo(fileRename);
            return "upload/" + fileRename.getName();
        } catch (IOException | ServletException e) {

        }
        return null;
    }

    private static File logInfo(Request req, Path tempFile, Part part) throws IOException, ServletException {
        File file = tempFile.toFile();
        return file;
    }

    private static String convertInputStreamToString(InputStream is) throws IOException {
        return IOUtils.toString(is, StandardCharsets.UTF_8);
    }

    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 1234; //return default port if heroku-port isn't set (i.e. on localhost)
    }
}
