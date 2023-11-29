package ua.in.sz.plugin;

import org.apache.commons.lang3.StringUtils;
import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.TaskAction;

import java.util.Optional;

public class GreetingTask extends DefaultTask {
    @TaskAction
    public void greet() {
        GreetingPluginExtension extension = Optional.ofNullable(getProject().getExtensions().findByType(GreetingPluginExtension.class))
                .orElse(new GreetingPluginExtension());

        getLogger().lifecycle("I am plugin. I have a message for you: [{}]", StringUtils.reverse(extension.getMessage()));
    }
}
