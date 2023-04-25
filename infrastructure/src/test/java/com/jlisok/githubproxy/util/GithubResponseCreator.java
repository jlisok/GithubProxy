package com.jlisok.githubproxy.util;

import com.jlisok.githubproxy.repositories.GithubRepositorySearchResult;
import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.RandomUtils;

import java.util.ArrayList;

@UtilityClass
public class GithubResponseCreator {
    public static GithubRepositorySearchResult.GithubRepositorySearchResultBuilder randomGithubRepositorySearchResult() {
        return GithubRepositorySearchResult.builder()
                .totalCount(RandomUtils.nextInt())
                .incompleteResults(RandomUtils.nextBoolean())
                .repositories(new ArrayList<>());
    }
}
