package com.jlisok.githubproxy.repositories.assemblers;

import com.jlisok.githubproxy.githubbranches.GithubBranchAssembler;
import com.jlisok.githubproxy.githubbranches.GithubBranchData;
import com.jlisok.githubproxy.repositories.RepositoryModel;
import com.jlisok.githubproxy.repositories.contracts.data.GithubRepositoryOwnerData;
import com.jlisok.githubproxy.repositories.contracts.data.GithubRepositoryResultData;
import com.jlisok.githubproxy.repositories.pojo.GithubRepositoryConstants;
import com.jlisok.githubproxy.repositories.pojo.GithubRepositoryRequest;
import com.jlisok.githubproxy.repositories.util.CollectionUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public final class GithubRepositoryAssembler {
    private final GithubBranchAssembler branchAssembler;

    public GithubRepositoryRequest toGithubRepositoryRequest(@NonNull String name, int pageNumber) {
        return GithubRepositoryRequest.builder()
                .pageNumber(pageNumber)
                .pageSize(GithubRepositoryConstants.SearchCriteria.PAGE_SIZE)
                .forkType(GithubRepositoryConstants.SearchCriteria.FORK_TYPE)
                .searchCriteria(toSearchCriteriaParam(name))
                .build();
    }

    public String toSearchCriteriaParam(@NonNull String userName) {
        var params = Optional.of(Map.entry(GithubRepositoryConstants.SearchCriteria.USER_NAME_KEY, userName))
                .map(entry -> entry.getKey() + ":" + entry.getValue())
                .orElse("");

        return URLEncoder.encode(params, StandardCharsets.UTF_8);
    }

    public RepositoryModel toRepositoryModel(GithubRepositoryResultData repository,
                                             Map<String, List<GithubBranchData>> branchesByRepositoryNames) {

        var ownerId = Optional.ofNullable(repository.getOwner())
                .map(GithubRepositoryOwnerData::getId)
                .orElse(null);

        var branches = branchesByRepositoryNames.getOrDefault(repository.getName(), Collections.emptyList());

        return RepositoryModel.builder()
                .name(repository.getName())
                .ownerId(ownerId)
                .branches(CollectionUtil.toList(branches, branchAssembler::toBranchModel))
                .build();
    }
}
