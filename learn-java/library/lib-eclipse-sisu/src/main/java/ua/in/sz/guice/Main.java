package ua.in.sz.guice;

import com.google.inject.Guice;

import javax.inject.Inject;
import javax.inject.Named;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.sisu.EagerSingleton;
import org.eclipse.sisu.space.SpaceModule;
import org.eclipse.sisu.space.URLClassSpace;
import org.eclipse.sisu.wire.WireModule;
import org.slf4j.bridge.SLF4JBridgeHandler;
import ua.in.sz.guice.service.ApplicationService;

import java.util.logging.Logger;

@Slf4j
@Named
@EagerSingleton
public class Main {

    @Inject
    public Main(ApplicationService applicationService) {
        applicationService.sendMessage("message", "mail");
    }

    public static void main(String[] args) {
        // Logger.getLogger("com.google.inject").addHandler(new SLF4JBridgeHandler());

        log.info("start main");

        ClassLoader classloader = Main.class.getClassLoader();
        Guice.createInjector(new WireModule(new SpaceModule(new URLClassSpace(classloader))));

        log.info("end main");
    }
}
