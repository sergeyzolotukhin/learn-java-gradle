package ua.in.sz.plugin;

import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.TaskAction;

import java.util.Optional;

public class GreetingTask extends DefaultTask {
    @TaskAction
    public void greet() {
        GreetingPluginExtension extension = Optional.ofNullable(getProject().getExtensions().findByType(GreetingPluginExtension.class))
                .orElse(new GreetingPluginExtension());

        getLogger().lifecycle("I am releasing build too! {}", extension.getMessage());
    }
}
