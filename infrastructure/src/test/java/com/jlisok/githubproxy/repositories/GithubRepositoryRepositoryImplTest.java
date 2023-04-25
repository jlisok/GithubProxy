package com.jlisok.githubproxy.repositories;

import com.jlisok.githubproxy.clients.GithubClient;
import com.jlisok.githubproxy.clients.PaginationUtil;
import com.jlisok.githubproxy.exceptions.GithubProxyException;
import com.jlisok.githubproxy.util.GithubRequestCreator;
import com.jlisok.githubproxy.util.GithubResponseCreator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.jlisok.githubproxy.clients.GithubClientConstants.DEFAULT_EMPTY_RESULT_PAGE_NUMBER;
import static com.jlisok.githubproxy.exceptions.ExceptionMessageConstants.GITHUB_UNAVAILABLE;
import static com.jlisok.githubproxy.exceptions.ExceptionMessageConstants.REPOSITORIES_NOT_FOUND;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GithubRepositoryRepositoryImplTest {
    @Mock
    private GithubClient client;
    @Mock
    private PaginationUtil paginationUtil;
    @InjectMocks
    private GithubRepositoryRepositoryImpl repository;

    @Test
    void getPagedGithubRepositoryData_serviceUnavailable() {
        // given
        var request = GithubRequestCreator.randomGithubRepositoryRequest().build();
        var response = GithubResponseCreator.randomGithubRepositorySearchResult().incompleteResults(true).build();

        when(client.getGithubRepositories(
                request.getSearchCriteria(),
                request.isForkType(),
                request.getPageSize(),
                request.getPageNumber())
        ).thenReturn(Optional.of(response));

        // when //then
        Assertions.assertThrows(GithubProxyException.class, () -> repository.getPagedGithubRepositoryData(request), GITHUB_UNAVAILABLE);
    }

    @Test
    void getPagedGithubRepositoryData_notFound() {
        // given
        var request = GithubRequestCreator.randomGithubRepositoryRequest().build();

        when(client.getGithubRepositories(
                request.getSearchCriteria(),
                request.isForkType(),
                request.getPageSize(),
                request.getPageNumber())
        ).thenReturn(Optional.empty());

        // when //then
        Assertions.assertThrows(GithubProxyException.class, () -> repository.getPagedGithubRepositoryData(request), REPOSITORIES_NOT_FOUND);
    }

    @Test
    void getPagedGithubRepositoryData_requestNull() {
        // when
        var result = repository.getPagedGithubRepositoryData(null);
        // then
        Assertions.assertNotNull(result);
        Assertions.assertEquals(DEFAULT_EMPTY_RESULT_PAGE_NUMBER, result.lastPageNumber());
        Assertions.assertEquals(DEFAULT_EMPTY_RESULT_PAGE_NUMBER, result.currentPageNumber());

        Assertions.assertNotNull(result.getData());
        Assertions.assertTrue(result.getData().isEmpty());
    }

    @Test
    void getPagedGithubRepositoryData_validCase() {
        //given
        var request = GithubRequestCreator.randomGithubRepositoryRequest().build();
        var response = GithubResponseCreator.randomGithubRepositorySearchResult().incompleteResults(false).build();
        var expectedLastPageNumber = 10;
        var expectedCurrentPageNumber = request.getPageNumber();

        when(client.getGithubRepositories(
                request.getSearchCriteria(),
                request.isForkType(),
                request.getPageSize(),
                request.getPageNumber())
        ).thenReturn(Optional.of(response));

        when(paginationUtil.getNextPageNumber(
                response.getTotalCount(),
                request.getPageSize())
        ).thenReturn(expectedLastPageNumber);

        // when
        var result = repository.getPagedGithubRepositoryData(request);
        // then
        Assertions.assertNotNull(result);
        Assertions.assertEquals(expectedLastPageNumber, result.lastPageNumber());
        Assertions.assertEquals(expectedCurrentPageNumber, result.currentPageNumber());

        Assertions.assertNotNull(result.getData());
    }
}
