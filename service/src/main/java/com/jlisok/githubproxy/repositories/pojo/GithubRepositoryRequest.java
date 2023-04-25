package com.jlisok.githubproxy.repositories.pojo;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class GithubRepositoryRequest {
    int pageSize;
    int pageNumber;
    boolean forkType;
    String searchCriteria;
}
