package ua.in.sz.h2;

import lombok.extern.slf4j.Slf4j;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.revwalk.RevTree;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;
import org.eclipse.jgit.treewalk.TreeWalk;

import java.io.File;

@Slf4j
public class Main {
    public static void main(String[] args) throws Exception {
        log.info("Starting Git");

        FileRepositoryBuilder builder = new FileRepositoryBuilder();
        Repository repository = builder.setGitDir(new File(".\\.git")).setMustExist(true).build();
        Git git = new Git(repository);
        Iterable<RevCommit> logg = git.log()
                .setMaxCount(1)
                .call();
        for (RevCommit rev : logg) {

            log.info("{}", rev.getId().getName());

            RevTree tree = rev.getTree();

            try (TreeWalk treeWalk = new TreeWalk(repository)) {
                treeWalk.addTree(tree);
                treeWalk.setRecursive(false);
                treeWalk.setPostOrderTraversal(false);

                while(treeWalk.next()) {
                    log.info("path: {}", treeWalk.getPathString());
                }
            }

        }
        git.close();

        log.info("Ending Git");
    }
}