package com.bazgab.status;


import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.Status;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;

import java.io.IOException;


public class ShowStatus {

    public static Repository openJGitRepository() throws IOException {
        FileRepositoryBuilder builder = new FileRepositoryBuilder();
        return builder
                .readEnvironment() // scan environment GIT_* variables
                .findGitDir() // scan up the file system tree
                .build();
    }


    public static void main(String[] args) throws GitAPIException {

        try (Repository repository = openJGitRepository()) {
            try (Git git = new Git(repository)) {
                Status status = git.status().call();
                System.out.println("=================================================================================================================");
                System.out.println("Running Tests on files with Modified Status...");
                System.out.println("Modified: " + status.getModified());
                System.out.println("=================================================================================================================");



            }
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}