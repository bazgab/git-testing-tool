package com.bazgab.gtt.branches;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.ListBranchCommand;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.lib.Ref;
import org.eclipse.jgit.lib.Repository;

import java.io.IOException;
import java.util.List;

import static com.bazgab.gtt.repositories.FindRepository.openJGitRepository;

public class ListBranches {

    public static void main(String[] args) throws IOException, GitAPIException {
        try (Repository repository = openJGitRepository()) {
            try (Git git = new Git(repository)) {
                System.out.println("Local branches:");
                List<Ref> call = git.branchList().call();
                for (Ref ref : call) {
                    System.out.printf("    %s\n", ref.getName());

                }

                System.out.println("Remote branches:");
                call = git.branchList().setListMode(ListBranchCommand.ListMode.ALL).call();
                for (Ref ref : call) {
                    System.out.printf("    %s\n", ref.getName());
                }
            }
        }
    }

}
