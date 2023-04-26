package com.jlisok.githubproxy.util;

import com.jlisok.githubproxy.branches.GithubBranch;
import com.jlisok.githubproxy.commits.GithubCommit;
import com.jlisok.githubproxy.repositories.GithubRepositoryOwner;
import com.jlisok.githubproxy.repositories.GithubRepositoryResult;
import com.jlisok.githubproxy.repositories.GithubRepositorySearchResult;
import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;

import java.util.List;

@UtilityClass
public class GithubResponseCreator {

    public GithubBranch.GithubBranchBuilder getGithubBranch() {
        return GithubBranch.builder()
                .commit(getGithubCommit().build())
                .name(RandomStringUtils.randomAlphabetic(10));
    }

    public GithubCommit.GithubCommitBuilder getGithubCommit() {
        return GithubCommit.builder()
                .sha(RandomStringUtils.randomAscii(20));
    }

    public GithubRepositorySearchResult.GithubRepositorySearchResultBuilder getGithubRepositorySearchResult() {
        return GithubRepositorySearchResult.builder()
                .totalCount(RandomUtils.nextInt())
                .incompleteResults(false)
                .repositories(List.of(getGithubRepositoryResult().build()));
    }

    public GithubRepositoryResult.GithubRepositoryResultBuilder getGithubRepositoryResult() {
        return GithubRepositoryResult.builder()
                .id(RandomUtils.nextLong())
                .name(RandomStringUtils.randomAlphabetic(10))
                .owner(getGithubRepositoryOwner().build());
    }

    public GithubRepositoryOwner.GithubRepositoryOwnerBuilder getGithubRepositoryOwner() {
        return GithubRepositoryOwner.builder()
                .id(RandomUtils.nextLong())
                .login(RandomStringUtils.randomAlphabetic(10));
    }
}
