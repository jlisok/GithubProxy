package com.jlisok.githubproxy.repositories.pojo;

import lombok.experimental.UtilityClass;

@UtilityClass
public class GithubRepositoryConstants {
    @UtilityClass
    public static class SearchCriteria {
        public static final int PAGE_SIZE = 10;
        public static final int DEFAULT_PAGE_NUMBER = 1;
        public static final String USER_NAME_KEY = "user";
        public static final boolean FORK_TYPE = false;
    }
}
