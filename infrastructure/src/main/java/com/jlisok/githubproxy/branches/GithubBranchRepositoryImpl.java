package com.jlisok.githubproxy.branches;

import com.jlisok.githubproxy.clients.GithubClient;
import com.jlisok.githubproxy.clients.PaginationUtil;
import com.jlisok.githubproxy.githubbranches.GithubBranchData;
import com.jlisok.githubproxy.githubbranches.GithubBranchRepository;
import com.jlisok.githubproxy.githubbranches.GithubBranchRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@RequiredArgsConstructor
public class GithubBranchRepositoryImpl implements GithubBranchRepository {
    private final GithubClient client;
    private final PaginationUtil paginationUtil;

    @Override
    public List<GithubBranchData> getGithubBranchData(GithubBranchRequest request) {
        if (Objects.isNull(request)) return Collections.emptyList();

        var hasNext = true;
        var pageNumber = request.getPageNumber();
        var result = new ArrayList<GithubBranchData>();
        while (hasNext) {
            var response = client.getGithubBranches(
                    request.getUsername(),
                    request.getRepositoryName(),
                    request.getPageSize(),
                    pageNumber
            );

            var body = Optional.ofNullable(response)
                    .map(ResponseEntity::getBody)
                    .orElse(Collections.emptyList());

            result.addAll(body);
            pageNumber = paginationUtil.getNextPageNumber(response);
            if (pageNumber < 2) hasNext = false;
        }

        return result;
    }
}
