package com.bazgab.gtt.repositories;

import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;

import java.io.IOException;

public class FindRepository {

    public static Repository openJGitRepository() throws IOException {
        FileRepositoryBuilder builder = new FileRepositoryBuilder();
        return builder
                .readEnvironment() // scan environment GIT_* variables
                .findGitDir() // scan up the file system tree
                .build();
    }

}
