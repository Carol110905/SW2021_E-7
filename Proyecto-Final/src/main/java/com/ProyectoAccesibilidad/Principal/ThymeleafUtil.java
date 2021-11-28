package com.ProyectoAccesibilidad.Principal;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.IContext;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

public class ThymeleafUtil {
    private static TemplateEngine templateEngine;

    public static void init() {

        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setTemplateMode(TemplateMode.HTML);
        templateResolver.setPrefix("templates/");

        templateResolver.setSuffix(".html");
        templateResolver.setCacheable(false);

        templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);
    }

    public static TemplateEngine getTemplateEngine() {

        if (templateEngine == null) {
            init();
        }
        return templateEngine;
    }

    public static String process(String template, IContext context) {

        return getTemplateEngine().process(template, context);

    }
}
