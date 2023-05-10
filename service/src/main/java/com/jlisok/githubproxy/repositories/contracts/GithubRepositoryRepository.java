package com.jlisok.githubproxy.repositories.contracts;

import com.jlisok.githubproxy.pagination.PagedData;
import com.jlisok.githubproxy.repositories.contracts.data.GithubRepositoryResultData;
import com.jlisok.githubproxy.repositories.pojo.GithubRepositoryRequest;

import java.util.List;

public interface GithubRepositoryRepository {
    PagedData<List<GithubRepositoryResultData>> getPagedGithubRepositoryData(GithubRepositoryRequest request);

}
