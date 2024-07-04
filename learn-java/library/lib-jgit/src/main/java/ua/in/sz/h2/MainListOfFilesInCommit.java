package ua.in.sz.h2;

import lombok.extern.slf4j.Slf4j;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.diff.DiffEntry;
import org.eclipse.jgit.diff.DiffFormatter;
import org.eclipse.jgit.diff.RawTextComparator;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.revwalk.RevWalk;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;
import org.eclipse.jgit.util.io.DisabledOutputStream;

import java.io.File;
import java.util.List;

@Slf4j
public class MainListOfFilesInCommit {
    public static void main(String[] args) throws Exception {
        log.info("Starting Git");

        FileRepositoryBuilder builder = new FileRepositoryBuilder();
        Repository repository = builder.setGitDir(new File(".\\.git")).setMustExist(true).build();
        Git git = new Git(repository);
        Iterable<RevCommit> logg = git.log()
                .setMaxCount(2)
                .call();
        for (RevCommit commit : logg) {
            log.info("{}", commit.getId().getName());


            RevWalk rw = new RevWalk(repository);
            RevCommit parent = rw.parseCommit(commit.getParent(0).getId());

            DiffFormatter df = new DiffFormatter(DisabledOutputStream.INSTANCE);
            df.setRepository(repository);
            df.setDiffComparator(RawTextComparator.DEFAULT);
            df.setDetectRenames(true);

            List<DiffEntry> diffs = df.scan(parent.getTree(), commit.getTree());
            for (DiffEntry diff : diffs) {
                log.info("{} {}", diff.getChangeType().name(), diff.getNewPath());
            }

            log.info("");
        }
        git.close();

        log.info("Add message");

        log.info("Ending Git");
    }
}