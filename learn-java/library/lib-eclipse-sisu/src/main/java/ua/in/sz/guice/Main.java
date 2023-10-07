package ua.in.sz.guice;

import com.google.inject.Guice;

import javax.inject.Inject;
import javax.inject.Named;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.sisu.EagerSingleton;
import org.eclipse.sisu.space.SpaceModule;
import org.eclipse.sisu.space.URLClassSpace;
import org.eclipse.sisu.wire.WireModule;
import ua.in.sz.guice.service.ApplicationService;

@Slf4j
@Named
@EagerSingleton
public class Main {

    @Inject
    public Main(ApplicationService applicationService) {
        applicationService.sendMessage("message", "mail");
    }

    public static void main(String[] args) {
        log.info("start main");

        ClassLoader classloader = Main.class.getClassLoader();
        Guice.createInjector(new WireModule(new SpaceModule(new URLClassSpace(classloader))));

        log.info("end main");
    }
}
