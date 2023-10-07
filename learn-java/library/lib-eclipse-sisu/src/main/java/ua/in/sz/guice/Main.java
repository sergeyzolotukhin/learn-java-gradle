package ua.in.sz.guice;

import com.google.inject.Guice;
import javax.inject.Named;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.sisu.EagerSingleton;
import org.eclipse.sisu.space.SpaceModule;
import org.eclipse.sisu.space.URLClassSpace;
import org.eclipse.sisu.wire.WireModule;

@Slf4j
@Named
@EagerSingleton
public class Main {

    public Main() {
        log.info("Started ---- >");
    }

    public static void main(String[] args) {
        log.info("start main");

        ClassLoader classloader = Main.class.getClassLoader();
        Guice.createInjector(new WireModule(new SpaceModule(new URLClassSpace(classloader))));

        log.info("end main");
    }
}
