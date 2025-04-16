package com.bazgab.lib;

import com.github.git24j.core.Init;
import com.github.git24j.core.Libgit2;

import java.nio.file.Path;
import java.nio.file.Paths;

public class LoadNativeLibs {

    // load native libraries
    Path distTarget = Paths.get("git-test/dist/git24j/target");

        Init.loadLibraries(distTarget.resolve("git2"), distTarget.resolve("git24j"));
        Libgit2.init();

}
