package com.jlisok.githubproxy.exceptions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class GithubProxyException extends RuntimeException {
    private final ExceptionCode code;
    private final int status;
    private final String message;
}
