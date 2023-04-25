package com.jlisok.githubproxy.facades;

import com.jlisok.githubproxy.accounts.AccountActivityModel;
import com.jlisok.githubproxy.githubbranches.GithubBranchService;
import com.jlisok.githubproxy.pagination.PaginationModel;
import com.jlisok.githubproxy.repositories.services.GithubRepositoryService;
import com.jlisok.githubproxy.repositories.util.GithubRepositoryDataUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.jlisok.githubproxy.repositories.pojo.GithubRepositoryConstants.SearchCriteria.DEFAULT_PAGE_NUMBER;

@Service
@RequiredArgsConstructor
public class GithubAccountFacade {
    private final GithubRepositoryService repositoryService;
    private final GithubBranchService branchService;
    private final GithubRepositoryDataUtil repositoryDataUtil;
    private final GithubAccountAssembler assembler;

    public PaginationModel<AccountActivityModel> getAccountActivity(String owner, Integer pageNumber) {
        var pageNumberRequest = Optional.ofNullable(pageNumber).orElse(DEFAULT_PAGE_NUMBER);
        var repositories = repositoryService.getGithubRepositories(owner, pageNumberRequest);
        var repositoryNames = repositoryDataUtil.extractRepositoryNames(repositories.getData());
        var branches = branchService.getAsyncGithubBranchesInRepositories(owner, repositoryNames);
        var owners = repositoryDataUtil.extractOwners(repositories.getData());

        return assembler.toPagedAccountActivityModel(repositories, branches, owners);
    }

}
