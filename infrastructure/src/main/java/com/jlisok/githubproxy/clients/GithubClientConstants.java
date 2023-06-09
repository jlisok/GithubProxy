package com.jlisok.githubproxy.clients;

import lombok.experimental.UtilityClass;

@UtilityClass
public class GithubClientConstants {
    public static final int DEFAULT_EMPTY_RESULT_PAGE_NUMBER = 0;

    public static final String CLIENT_NAME = "github";
    public static final String GET_REPOSITORIES_BY_SEARCH_CRITERIA = "/search/repositories";
    public static final String GET_BRANCHES_IN_REPOSITORY = "/repos/{owner}/{name}/branches";
    public static final String PAGE_NUMBER_NAME = "page";

}
