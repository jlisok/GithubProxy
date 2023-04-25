package com.jlisok.githubproxy.repositories.constracts;

import java.util.List;

public interface GithubRepositorySearchData {
    Integer getTotalCount();

    Boolean getIncompleteResults();

    List<GithubRepositoryResultData> getRepositoryData();
}
