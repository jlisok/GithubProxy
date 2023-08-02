package com.jlisok.githubproxy.repositories.util;

import com.jlisok.githubproxy.repositories.contracts.data.GithubRepositoryOwnerData;
import com.jlisok.githubproxy.repositories.contracts.data.GithubRepositoryResultData;
import jakarta.annotation.Nullable;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class GithubRepositoryDataUtil {

    public List<String> extractRepositoryNames(@Nullable List<GithubRepositoryResultData> repositories) {
        return Optional.ofNullable(repositories)
                .orElseGet(Collections::emptyList).stream()
                .map(GithubRepositoryResultData::getName)
                .filter(Objects::nonNull)
                .filter(Strings::isNotBlank)
                .toList();
    }

    public Set<GithubRepositoryOwnerData> extractOwners(@Nullable List<GithubRepositoryResultData> repositories) {
        return Optional.ofNullable(repositories)
                .orElseGet(Collections::emptyList).stream()
                .map(GithubRepositoryResultData::getOwner)
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());
    }
}
