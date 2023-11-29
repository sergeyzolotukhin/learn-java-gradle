package ua.in.sz.plugin;

import org.gradle.api.Project;
import org.gradle.api.Plugin;
import org.jetbrains.annotations.NotNull;

public class GreetingPlugin implements Plugin<Project> {
    @Override
    public void apply(@NotNull Project project) {
        project.getLogger().lifecycle("I am releasing build too!");
    }
}
