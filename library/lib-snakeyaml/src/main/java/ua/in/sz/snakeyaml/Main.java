package ua.in.sz.snakeyaml;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.nodes.Tag;

import java.io.InputStream;
import java.io.StringWriter;
import java.util.Iterator;
import java.util.Map;

@Slf4j
public class Main {
    @SneakyThrows
    public static void main(String[] args) {
        LoaderOptions loaderOptions = new LoaderOptions();
        Yaml yaml = new Yaml(new Constructor(Derivation.class, loaderOptions));
        InputStream inputStream = Main.class
                .getClassLoader()
                .getResourceAsStream("customer.yaml");

        Iterable<Object> iterable = yaml.loadAll(inputStream);
//        for (Object o : iterable) {
//            log.info("YAML: {}", o);
//        }

//        StringWriter writer = new StringWriter();
        Iterator<Object> it = iterable.iterator();
        String text = yaml.dumpAs(it, Tag.MAP, null);
        log.info("YAML text:{}", text);
    }
}