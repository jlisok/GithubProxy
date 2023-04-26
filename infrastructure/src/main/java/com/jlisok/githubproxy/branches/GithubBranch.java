package com.jlisok.githubproxy.branches;

import com.jlisok.githubproxy.commits.GithubCommit;
import com.jlisok.githubproxy.githubbranches.GithubBranchData;
import com.jlisok.githubproxy.githubbranches.GithubCommitData;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.beans.Transient;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GithubBranch implements GithubBranchData {
    private String name;
    private GithubCommit commit;

    @Override
    @Transient
    public GithubCommitData getGithubCommitData() {
        return commit;
    }
}
