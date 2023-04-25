package com.jlisok.githubproxy.util;

import com.jlisok.githubproxy.repositories.pojo.GithubRepositoryRequest;
import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;

@UtilityClass
public class GithubRequestCreator {

    public static GithubRepositoryRequest.GithubRepositoryRequestBuilder randomGithubRepositoryRequest() {
        return GithubRepositoryRequest.builder()
                .forkType(RandomUtils.nextBoolean())
                .pageNumber(RandomUtils.nextInt())
                .pageSize(RandomUtils.nextInt())
                .searchCriteria(RandomStringUtils.randomAlphanumeric(20));
    }
}
