package ua.in.sz.plugin;

import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.TaskAction;

public class GreetingTask extends DefaultTask {
    @TaskAction
    public void greet() {
        getLogger().lifecycle("I am releasing build too!");
    }
}
