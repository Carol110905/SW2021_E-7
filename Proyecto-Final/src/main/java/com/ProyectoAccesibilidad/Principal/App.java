package com.ProyectoAccesibilidad.Principal;

import static spark.Spark.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.thymeleaf.context.Context;
import org.thymeleaf.context.IContext;
//import spark.ModelAndView;
/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        port(1234);	
        staticFiles.location("/");

        get("/",(rq, rs)->{
            rs.redirect("/login.html");
            return null;
        });

        get("/validar",(rq, rs)->{
            String usuario = rq.queryParams("usuario");
            String password = rq.queryParams("password");
            if(usuario.equals("Joaquin") && password.equals("123456")){
                System.out.println("Si llegue");
            }else{
                Map<String, Object> variables = new HashMap<>();
                IContext context = new Context(rq.raw().getLocale(), variables);
                String out = ThymeleafUtil.getTemplateEngine().process("Hola", context);
                return out;
            }
            return null;
        });
    }
}
