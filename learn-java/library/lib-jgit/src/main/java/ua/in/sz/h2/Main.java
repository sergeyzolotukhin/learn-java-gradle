package ua.in.sz.h2;

import lombok.extern.slf4j.Slf4j;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;

import java.io.File;

@Slf4j
public class Main {
    public static void main(String[] args) throws Exception {
        log.info("Starting Git");

        FileRepositoryBuilder builder = new FileRepositoryBuilder();
        Repository repo = builder.setGitDir(new File(".\\.git")).setMustExist(true).build();
        Git git = new Git(repo);
        Iterable<RevCommit> logg = git.log().call();
        int i = 5;
        for (RevCommit rev : logg) {
            log.info("{}", rev.getFullMessage());
            i--;
            if (i <= 0) {
                break;
            }
        }
        git.close();

        log.info("Ending Git");
    }
}