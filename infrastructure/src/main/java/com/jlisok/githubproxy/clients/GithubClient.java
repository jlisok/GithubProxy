package com.jlisok.githubproxy.clients;

import com.jlisok.githubproxy.branches.GithubBranch;
import com.jlisok.githubproxy.repositories.GithubRepositorySearchResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

import static com.jlisok.githubproxy.clients.GithubClientConstants.*;

@FeignClient(
        value = CLIENT_NAME,
        url = "${integrations.github.url}"
)
public interface GithubClient {

    @GetMapping(value = GET_REPOSITORIES_BY_SEARCH_CRITERIA)
    Optional<GithubRepositorySearchResult> getGithubRepositories(@RequestParam("q") String searchCriteria,
                                                                 @RequestParam("fork") boolean forkTypeIncluded,
                                                                 @RequestParam("per_page") int pageSize,
                                                                 @RequestParam("page") int pageNumber);

    @GetMapping(value = GET_BRANCHES_IN_REPOSITORY)
    ResponseEntity<List<GithubBranch>> getGithubBranches(@PathVariable("owner") String repositoryOwner,
                                                         @PathVariable("name") String repositoryName,
                                                         @RequestParam("per_page") int pageSize,
                                                         @RequestParam("page") int pageNumber);
}
