package com.jlisok.githubproxy.githubbranches;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class GithubBranchRequest {
    int pageSize;
    int pageNumber;
    String username;
    String repositoryName;
}
