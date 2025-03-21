package ua.in.sz.plugin;

import org.gradle.api.Project;
import org.gradle.api.Plugin;
import org.gradle.api.logging.Logger;
import org.jetbrains.annotations.NotNull;

public class GreetingPlugin implements Plugin<Project> {
    @Override
    public void apply(@NotNull Project project) {
//        Logger log = project.getLogger();

        project.getExtensions().create("greeting", GreetingPluginExtension.class);
        GreetingTask hello = project.getTasks().create("hello", GreetingTask.class);
        hello.setGroup("ge");

//        log.lifecycle("=====================");
    }
}
