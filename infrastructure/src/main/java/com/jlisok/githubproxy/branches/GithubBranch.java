package com.jlisok.githubproxy.branches;

import com.jlisok.githubproxy.commits.GithubCommit;
import com.jlisok.githubproxy.githubbranches.GithubBranchData;
import com.jlisok.githubproxy.githubbranches.GithubCommitData;
import lombok.Data;

@Data
public class GithubBranch implements GithubBranchData {
    private String name;
    private GithubCommit commit;

    @Override
    public GithubCommitData getGithubCommitData() {
        return commit;
    }
}
