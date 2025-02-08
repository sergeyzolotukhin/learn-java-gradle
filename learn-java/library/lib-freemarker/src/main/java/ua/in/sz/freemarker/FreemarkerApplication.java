package ua.in.sz.freemarker;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

@Slf4j
public class FreemarkerApplication {
    public static final String LIB_FREEMARKER = "learn-java/library/lib-freemarker";

    public static void main(String[] args) throws Exception {
        Configuration cfg = createConfiguration();

        Map<Object, Object> root = new HashMap<>();
        root.put("user", "Big Joe");
        root.put("latestProduct",
                Product.builder()
                        .url("products/greenmouse.html")
                        .name("green mouse")
                        .build());

        Template template = cfg.getTemplate("test.ftl");

        Writer out = new OutputStreamWriter(System.out);
        template.process(root, out);
    }

    private static Configuration createConfiguration() throws IOException {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_34);
        cfg.setDirectoryForTemplateLoading(new File(LIB_FREEMARKER + "/src/main/resources"));
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        cfg.setLogTemplateExceptions(false);
        cfg.setWrapUncheckedExceptions(true);
        cfg.setFallbackOnNullLoopVariable(false);
        cfg.setSQLDateAndTimeTimeZone(TimeZone.getDefault());
        return cfg;
    }

    @Setter
    @Getter
    @Builder
    public static class Product {
        private String url;
        private String name;
    }
}
