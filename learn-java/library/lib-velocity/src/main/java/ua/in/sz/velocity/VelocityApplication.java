package ua.in.sz.velocity;


import lombok.extern.slf4j.Slf4j;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import java.io.StringWriter;
import java.io.Writer;

@Slf4j
public class VelocityApplication {
    public static final String LIB_FREEMARKER = "learn-java/library/lib-velocity";

    public static void main(String[] args) throws Exception {
        VelocityEngine velocityEngine = new VelocityEngine();
        velocityEngine.init();

        Template t = velocityEngine.getTemplate(LIB_FREEMARKER + "/src/main/resources/" + "index.vm");

        VelocityContext context = new VelocityContext();
        context.put("name", "World");

        Writer writer = new StringWriter();
        t.merge(context, writer);
        log.info("{}", writer);
    }
}
