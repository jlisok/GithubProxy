package com.jlisok.githubproxy.repositories.constracts;

public interface GithubRepositoryResultData {
    Long getId();

    String getName();

    GithubRepositoryOwnerData getOwner();
}
