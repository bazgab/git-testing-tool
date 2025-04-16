package com.bazgab.gtt.status;


import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.Status;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.lib.Repository;

import java.io.IOException;

import static com.bazgab.gtt.repositories.FindRepository.openJGitRepository;


public class ShowStatus {

    public static void main(String[] args) throws GitAPIException {

        try (Repository repository = openJGitRepository()) {
            try (Git git = new Git(repository)) {
                Status status = git.status().call();
                System.out.println("=================================================================================================================");
                System.out.println("Running Tests on files with Modified Status...");
                System.out.println("=================================================================================================================");
                System.out.println("Modified: " + status.getModified());
                System.out.println("=================================================================================================================");



            }
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}