package ua.in.sz.jgroup;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;

import java.util.Arrays;

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


}