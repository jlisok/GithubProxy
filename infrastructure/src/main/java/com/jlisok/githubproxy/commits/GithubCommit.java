package com.jlisok.githubproxy.commits;

import com.jlisok.githubproxy.githubbranches.GithubCommitData;
import lombok.Data;

@Data
public class GithubCommit implements GithubCommitData {
    private String sha;
}
