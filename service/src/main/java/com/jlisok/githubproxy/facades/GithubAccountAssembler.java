package com.jlisok.githubproxy.facades;

import com.jlisok.githubproxy.accounts.AccountActivityModel;
import com.jlisok.githubproxy.githubbranches.GithubBranchData;
import com.jlisok.githubproxy.pagination.PagedData;
import com.jlisok.githubproxy.pagination.PaginationModel;
import com.jlisok.githubproxy.repositories.RepositoryModel;
import com.jlisok.githubproxy.repositories.assemblers.GithubRepositoryAssembler;
import com.jlisok.githubproxy.repositories.constracts.GithubRepositoryOwnerData;
import com.jlisok.githubproxy.repositories.constracts.GithubRepositoryResultData;
import com.jlisok.githubproxy.repositories.util.CollectionUtil;
import com.jlisok.githubproxy.users.GithubUserAssembler;
import com.jlisok.githubproxy.users.UserModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class GithubAccountAssembler {
    private final GithubRepositoryAssembler repositoryAssembler;
    private final GithubUserAssembler userAssembler;

    PaginationModel<AccountActivityModel> toPagedAccountActivityModel(PagedData<List<GithubRepositoryResultData>> pagedRepositories,
                                                                      Map<String, List<GithubBranchData>> branchesByRepositoryNames,
                                                                      Set<GithubRepositoryOwnerData> owners) {

        var users = CollectionUtil.toList(owners, userAssembler::toUserModel);
        var repositories = CollectionUtil.toList(
                pagedRepositories.getData(),
                item -> repositoryAssembler.toRepositoryModel(item, branchesByRepositoryNames)
        );


        return new PaginationModel<>(
                pagedRepositories.hasNext(),
                pagedRepositories.currentPageNumber(),
                toAccountActivityModel(users, repositories)
        );
    }

    AccountActivityModel toAccountActivityModel(List<UserModel> users, List<RepositoryModel> repositories) {
        return AccountActivityModel.builder()
                .users(users)
                .repositories(repositories)
                .build();
    }

}
