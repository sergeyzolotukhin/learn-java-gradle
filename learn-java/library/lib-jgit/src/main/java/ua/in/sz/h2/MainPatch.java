package ua.in.sz.h2;

import lombok.extern.slf4j.Slf4j;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.diff.DiffEntry;
import org.eclipse.jgit.diff.DiffFormatter;
import org.eclipse.jgit.diff.RawTextComparator;
import org.eclipse.jgit.lib.ObjectReader;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.revwalk.RevTree;
import org.eclipse.jgit.revwalk.RevWalk;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;
import org.eclipse.jgit.treewalk.AbstractTreeIterator;
import org.eclipse.jgit.treewalk.CanonicalTreeParser;
import org.eclipse.jgit.util.io.DisabledOutputStream;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Slf4j
public class MainPatch {
    public static void main(String[] args) throws Exception {
        log.info("Starting Git");

        FileRepositoryBuilder builder = new FileRepositoryBuilder();
        Repository repository = builder.setGitDir(new File(".\\.git")).setMustExist(true).build();
        Git git = new Git(repository);
        Iterable<RevCommit> gitLog = git.log()
                .setMaxCount(1)
                .call();
        for (RevCommit commit : gitLog) {
            log.info("{}", commit.getId().getName());

            RevWalk walk = new RevWalk(repository);
            RevCommit parent = walk.parseCommit(commit.getParent(0).getId());

            List<DiffEntry> diffs = getDiff(repository, commit.getId().getName(), parent.getId().getName());

            DiffFormatter df = new DiffFormatter(System.out);
            df.setRepository(repository);
            df.setDiffComparator(RawTextComparator.DEFAULT);
            df.setDetectRenames(true);


            for (DiffEntry diff : diffs) {
                df.format(diff);
            }

            log.info("");
        }
        git.close();

        log.info("Ending Git");
    }

    private static List<DiffEntry> getDiff(Repository repository, String after, String before) throws IOException, GitAPIException {
        Git git = new Git(repository);
        return git.diff()
                .setOldTree(prepareTreeParser(repository, before))
                .setNewTree(prepareTreeParser(repository, after))
                .call();
    }

    private static AbstractTreeIterator prepareTreeParser(Repository repository, String objectId) throws IOException {
        try (RevWalk walk = new RevWalk(repository)) {
            RevCommit commit = walk.parseCommit(repository.resolve(objectId));
            RevTree tree = walk.parseTree(commit.getTree().getId());

            CanonicalTreeParser treeParser = new CanonicalTreeParser();
            try (ObjectReader reader = repository.newObjectReader()) {
                treeParser.reset(reader, tree.getId());
            }

            walk.dispose();

            return treeParser;
        }
    }
}