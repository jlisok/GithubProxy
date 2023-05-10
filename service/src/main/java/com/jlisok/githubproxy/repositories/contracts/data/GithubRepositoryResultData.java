package com.jlisok.githubproxy.repositories.contracts.data;

public interface GithubRepositoryResultData {
    Long getId();

    String getName();

    GithubRepositoryOwnerData getOwner();
}
