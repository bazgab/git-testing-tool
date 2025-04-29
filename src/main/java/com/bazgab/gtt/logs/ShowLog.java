package com.bazgab.gtt.logs;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.lib.PersonIdent;
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
                PersonIdent commitData = new PersonIdent(repository);
                System.out.println(commitData);

                for (RevCommit rev : logs) {
                    PersonIdent AuthorIdent = rev.getAuthorIdent();
                    String CommitTime = String.valueOf(AuthorIdent.getWhen());

                    System.out.println(rev
                            + " Id: " + rev.getId().getName()
                            + " Commited at: " + CommitTime);
                    count++;
                }
                // Print out commit count
                System.out.println("Had " + count + " commits overall on current branch");
                System.out.println("Had " + count + " commits overall in repository");

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




