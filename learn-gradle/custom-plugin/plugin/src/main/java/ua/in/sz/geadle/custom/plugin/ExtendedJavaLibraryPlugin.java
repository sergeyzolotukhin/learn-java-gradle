package ua.in.sz.geadle.custom.plugin;

import org.gradle.api.Project;
import org.gradle.api.Plugin;
import org.gradle.api.plugins.JavaLibraryPlugin;

public class ExtendedJavaLibraryPlugin implements Plugin<Project> {
    public void apply(Project project) {
        project.getPluginManager().apply(JavaLibraryPlugin.class);

        project.getTasks().register("javaVersion", task -> {
            task.setGroup("help");
            task.setDescription("Get the java version");
            task.doLast(s -> System.out.println("Java version by serhij zolotukhin 2-" + System.getProperty("java.version")));
        });
    }
}
