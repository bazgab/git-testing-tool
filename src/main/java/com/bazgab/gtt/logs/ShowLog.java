package com.bazgab.gtt.logs;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;

import java.io.IOException;

import static com.bazgab.gtt.repositories.FindRepository.openJGitRepository;

public class ShowLog {

    @SuppressWarnings("unused")
    public static void main(String[] args) throws IOException, GitAPIException {
        try (Repository repository = openJGitRepository()) {
            try (Git git = new Git(repository)) {
                Iterable<RevCommit> logs = git.log()
                        .call();
                int count = 0;
                for (RevCommit rev : logs) {
                    //System.out.println("Commit: " + rev /* + ", name: " + rev.getName() + ", id: " + rev.getId().getName() */);
                    count++;
                }
                System.out.println("Had " + count + " commits overall on current branch");

                System.out.println("Listing commits on testbranch");
                logs = git.log()
                        .add(repository.resolve("remotes/origin/testbranch"))
                        .call();
                count = 0;
                for (RevCommit rev : logs) {
                    System.out.println("Commit: " + rev /* + ", name: " + rev.getName() + ", id: " + rev.getId().getName() */);
                    count++;
                }
                System.out.println("Had " + count + " commits overall on test-branch");

                System.out.println("Listing commits on testbranch but not on master (i.e. unmerged)");
                logs = git.log()
                        .not(repository.resolve("master"))
                        .add(repository.resolve("remotes/origin/testbranch"))
                        .call();
                count = 0;
                for (RevCommit rev : logs) {
                    System.out.println("Commit: " + rev /* + ", name: " + rev.getName() + ", id: " + rev.getId().getName() */);
                    count++;
                }
                System.out.println("Had " + count + " commits only on test-branch");

                logs = git.log()
                        .all()
                        .call();
                count = 0;
                for (RevCommit rev : logs) {
                    //System.out.println("Commit: " + rev /* + ", name: " + rev.getName() + ", id: " + rev.getId().getName() */);
                    count++;
                }
                System.out.println("Had " + count + " commits overall in repository");

                logs = git.log()
                        // for all log.all()
                        .addPath("README.md")
                        .call();
                count = 0;
                for (RevCommit rev : logs) {
                    //System.out.println("Commit: " + rev /* + ", name: " + rev.getName() + ", id: " + rev.getId().getName() */);
                    count++;
                }
                System.out.println("Had " + count + " commits on README.md");

                logs = git.log()
                        // for all log.all()
                        .addPath("pom.xml")
                        .call();
                count = 0;
                for (RevCommit rev : logs) {
                    //System.out.println("Commit: " + rev /* + ", name: " + rev.getName() + ", id: " + rev.getId().getName() */);
                    count++;
                }
                System.out.println("Had " + count + " commits on pom.xml");

                // then produce a "classic" Git commit log
                // see also https://stackoverflow.com/a/69138290/411846
                System.out.println();
                System.out.println("Print log of commits on testbranch");
                //logs = git.log()
                      //  .add(repository.resolve("remotes/origin/testbranch"))
                     //   .call();
                //for (RevCommit rev : logs) {
                  //  System.out.println(getConventionalCommitMessage(rev));
                }
            }
        }
    }

   /*
   TODO: Replace deprecated modules on getConventialCommitMessage;

   private static String getConventionalCommitMessage(RevCommit commit) {
        StringBuilder stringBuilder = new StringBuilder();

        // Prepare the pieces
        final String justTheAuthorNoTime = commit.getAuthorIdent().toExternalString().split(">")[0] + ">";
        final Instant commitInstant = Instant.ofEpochSecond(commit.getCommitTime());
        final ZoneId zoneId = commit.getAuthorIdent().getTimeZone().toZoneId();
        final ZonedDateTime authorDateTime = ZonedDateTime.ofInstant(commitInstant, zoneId);
        final String gitDateTimeFormatString = "EEE MMM dd HH:mm:ss yyyy Z";
        final String formattedDate = authorDateTime.format(DateTimeFormatter.ofPattern(gitDateTimeFormatString));
        final String tabbedCommitMessage =
                Arrays.stream(commit.getFullMessage()
                                .split("\\r?\\n")) // split it up by line
                        .map(s -> "\t" + s + "\n") // add a tab on each line
                        .collect(Collectors.joining()); // put it back together

        // Put pieces together
        stringBuilder
                .append("commit ").append(commit.getName()).append("\n")
                .append("Author:\t").append(justTheAuthorNoTime).append("\n")
                .append("Date:\t").append(formattedDate).append("\n\n")
                .append(tabbedCommitMessage);

        return stringBuilder.toString();
    }*/




