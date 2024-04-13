package ua.in.sz.jgroup;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;

import java.io.File;

@Slf4j
public class Main {

    public static void main(String[] args) throws Exception {
        CommandLine mvn = new CommandLine("mvn");
        mvn.addArgument("--version");

        CommandLine cmd = new CommandLine("cmd");
        cmd.addArgument("/c");
        cmd.addArguments(mvn.toStrings(), true);

        DefaultExecutor executor = new DefaultExecutor();
        int exitCode = executor.execute(cmd);

        log.info("Command executed: {} with exit code: {}", cmd, exitCode);
    }

    private static void executeClassOutsideAjar() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        String classpathEnv = System.getenv("CLASSPATH");
        log.info("classpathEnv: {}", classpathEnv);

        String classpath = System.getProperty("java.class.path");
        String[] classpathEntries = classpath.split(File.pathSeparator);
        for (String classpathEntry : classpathEntries) {
            log.info("Classpath entry: {}", classpathEntry);
        }

        Object newObject = Class.forName("b.DebugSupport").newInstance();
    }
}