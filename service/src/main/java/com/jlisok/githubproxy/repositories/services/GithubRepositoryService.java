package com.jlisok.githubproxy.repositories.services;

import com.jlisok.githubproxy.pagination.PagedData;
import com.jlisok.githubproxy.repositories.assemblers.GithubRepositoryAssembler;
import com.jlisok.githubproxy.repositories.constracts.GithubRepositoryRepository;
import com.jlisok.githubproxy.repositories.constracts.GithubRepositoryResultData;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GithubRepositoryService {
    private final GithubRepositoryRepository repository;
    private final GithubRepositoryAssembler assembler;

    public PagedData<List<GithubRepositoryResultData>> getGithubRepositories(@NonNull String name, int pageNumber) {
        var request = assembler.toGithubRepositoryRequest(name, pageNumber);

        return repository.getPagedGithubRepositoryData(request);
    }
}
