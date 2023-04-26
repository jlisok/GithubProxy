package com.jlisok.githubproxy.commits;

import com.jlisok.githubproxy.githubbranches.GithubCommitData;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GithubCommit implements GithubCommitData {
    private String sha;
}
