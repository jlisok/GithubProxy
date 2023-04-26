package com.jlisok.githubproxy.mock;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.jlisok.githubproxy.branches.GithubBranch;
import com.jlisok.githubproxy.repositories.GithubRepositorySearchResult;
import com.jlisok.githubproxy.util.GithubResponseCreator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.jlisok.githubproxy.clients.GithubClientConstants.GET_REPOSITORIES_BY_SEARCH_CRITERIA;

@Component
@RequiredArgsConstructor
public class GithubStubMock {
    private final ObjectMapper mapper;

    public GithubRepositorySearchResult stubRepositoryResponse() throws JsonProcessingException {
        var testRepository = GithubResponseCreator.getGithubRepositorySearchResult().totalCount(1).build();

        WireMock.stubFor(WireMock.get(WireMock.urlPathEqualTo(GET_REPOSITORIES_BY_SEARCH_CRITERIA))
                .willReturn(
                        WireMock.aResponse()
                                .withStatus(HttpStatus.OK.value())
                                .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                                .withBody(mapper.writeValueAsString(testRepository))
                )
        );

        return testRepository;
    }

    public GithubBranch stubBranchResponse(String repositoryName, String ownerName) throws JsonProcessingException {
        var testBranch = GithubResponseCreator.getGithubBranch().name(repositoryName).build();

        WireMock.stubFor(WireMock.get(WireMock.urlPathEqualTo("/repos/" + ownerName + "/" + repositoryName + "/branches"))
                .willReturn(
                        WireMock.aResponse()
                                .withStatus(HttpStatus.OK.value())
                                .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                                .withBody(mapper.writeValueAsString(List.of(testBranch)))
                )
        );

        return testBranch;
    }

    public void stubRepositoryResponseStatus(HttpStatus status) {

        WireMock.stubFor(WireMock.get(WireMock.urlPathEqualTo(GET_REPOSITORIES_BY_SEARCH_CRITERIA))
                .willReturn(
                        WireMock.aResponse()
                                .withStatus(status.value())
                                .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                )
        );
    }
}
