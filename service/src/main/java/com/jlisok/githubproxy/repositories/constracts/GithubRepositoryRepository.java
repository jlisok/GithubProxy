package com.jlisok.githubproxy.repositories.constracts;

import com.jlisok.githubproxy.pagination.PagedData;
import com.jlisok.githubproxy.repositories.pojo.GithubRepositoryRequest;

import java.util.List;

public interface GithubRepositoryRepository {
    PagedData<List<GithubRepositoryResultData>> getPagedGithubRepositoryData(GithubRepositoryRequest request);

}
