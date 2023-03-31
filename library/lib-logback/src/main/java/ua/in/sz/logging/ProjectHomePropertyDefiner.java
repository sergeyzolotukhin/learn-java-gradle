package ua.in.sz.logging;

import ch.qos.logback.core.PropertyDefinerBase;
import lombok.Setter;

import java.nio.file.Path;
import java.nio.file.Paths;

public class ProjectHomePropertyDefiner extends PropertyDefinerBase {
    @Setter
    private String start = System.getProperty("user.dir");

    @Setter
    private String marker = "settings.gradle";

    @Override
    public String getPropertyValue() {
        Path path = Paths.get(Paths.get(start).toUri());
        while (path != null) {
            if (path.resolve(marker).toFile().exists()) {
                return path.toAbsolutePath().toString();
            }
            path = path.getParent();
        }

        throw new IllegalStateException("Home not found");
    }
}
