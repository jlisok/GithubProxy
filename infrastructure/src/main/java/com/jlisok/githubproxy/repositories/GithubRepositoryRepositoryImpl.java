package com.jlisok.githubproxy.repositories;

import com.jlisok.githubproxy.clients.GithubClient;
import com.jlisok.githubproxy.clients.PaginationUtil;
import com.jlisok.githubproxy.exceptions.ExceptionCode;
import com.jlisok.githubproxy.exceptions.GithubProxyException;
import com.jlisok.githubproxy.pagination.PagedData;
import com.jlisok.githubproxy.repositories.constracts.GithubRepositoryRepository;
import com.jlisok.githubproxy.repositories.constracts.GithubRepositoryResultData;
import com.jlisok.githubproxy.repositories.pojo.GithubRepositoryRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static com.jlisok.githubproxy.clients.GithubClientConstants.DEFAULT_EMPTY_RESULT_PAGE_NUMBER;
import static com.jlisok.githubproxy.exceptions.ExceptionMessageConstants.GITHUB_UNAVAILABLE;
import static com.jlisok.githubproxy.exceptions.ExceptionMessageConstants.REPOSITORIES_NOT_FOUND;

@Component
@RequiredArgsConstructor
public class GithubRepositoryRepositoryImpl implements GithubRepositoryRepository {
    private final GithubClient client;
    private final PaginationUtil paginationUtil;


    @Override
    public PagedData<List<GithubRepositoryResultData>> getPagedGithubRepositoryData(GithubRepositoryRequest request) {
        if (Objects.isNull(request)) {
            return new PagedData<>(Collections.emptyList(), DEFAULT_EMPTY_RESULT_PAGE_NUMBER, DEFAULT_EMPTY_RESULT_PAGE_NUMBER, false);
        }

        var response = client.getGithubRepositories(request.getSearchCriteria(), request.isForkType(), request.getPageSize(), request.getPageNumber())
                .orElseThrow(() -> new GithubProxyException(ExceptionCode.OBJECT_NOT_FOUND, HttpURLConnection.HTTP_NOT_FOUND,
                        REPOSITORIES_NOT_FOUND));

        if (response.getIncompleteResults() != null && response.getIncompleteResults()) {
            throw new GithubProxyException(ExceptionCode.GITHUB_API_EXCEPTION, HttpURLConnection.HTTP_UNAVAILABLE, GITHUB_UNAVAILABLE);
        }

        var lastPageNumber = paginationUtil.getNextPageNumber(response.getTotalCount(), request.getPageSize());

        return new PagedData<>(
                new ArrayList<>(response.getRepositories()),
                request.getPageNumber(),
                lastPageNumber,
                lastPageNumber > request.getPageNumber()
        );
    }
}
