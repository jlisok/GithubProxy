package com.jlisok.githubproxy.exceptions;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ExceptionMessageConstants {
    public static final String GITHUB_UNAVAILABLE = "GithubApi unavailable";
    public static final String REPOSITORIES_NOT_FOUND = "No repositories found for the given user name";
    public static final String BAD_REQUEST = "Bad request";
    public static final String GENERIC_MICROSERVICE_EXCEPTION = "Ops. Something went wrong with GitHubProxy";
    public static final String MEDIA_TYPE_NOT_SUPPORTED = "Given Accept Media type is not supported";
}
