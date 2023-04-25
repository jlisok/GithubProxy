package com.jlisok.githubproxy.githubbranches;

import com.jlisok.githubproxy.repositories.BranchModel;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static com.jlisok.githubproxy.repositories.pojo.GithubRepositoryConstants.SearchCriteria.DEFAULT_PAGE_NUMBER;
import static com.jlisok.githubproxy.repositories.pojo.GithubRepositoryConstants.SearchCriteria.PAGE_SIZE;

@Component
public final class GithubBranchAssembler {

    public GithubBranchRequest toGithubBranchRequest(@NonNull String name, @NonNull String repositoryName) {
        return GithubBranchRequest.builder()
                .pageNumber(DEFAULT_PAGE_NUMBER)
                .pageSize(PAGE_SIZE)
                .username(name)
                .repositoryName(repositoryName)
                .build();
    }

    public BranchModel toBranchModel(GithubBranchData branch) {
        var commitSha = Optional.ofNullable(branch.getGithubCommitData())
                .map(GithubCommitData::getSha)
                .orElse(null);

        return BranchModel.builder()
                .name(branch.getName())
                .lastCommitHash(commitSha)
                .build();
    }
}
