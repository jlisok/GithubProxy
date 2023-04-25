package com.jlisok.githubproxy.githubbranches;

import java.util.List;

public interface GithubBranchRepository {

    List<GithubBranchData> getGithubBranchData(GithubBranchRequest request);
}
