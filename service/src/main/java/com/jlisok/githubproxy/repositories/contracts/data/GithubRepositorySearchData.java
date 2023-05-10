package com.jlisok.githubproxy.repositories.contracts.data;

import java.util.List;

public interface GithubRepositorySearchData {
    Integer getTotalCount();

    Boolean getIncompleteResults();

    List<GithubRepositoryResultData> getRepositoryData();
}
