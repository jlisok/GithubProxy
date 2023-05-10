package com.jlisok.githubproxy.repositories.util.data;

import com.jlisok.githubproxy.repositories.contracts.data.GithubRepositoryResultData;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TestGithubRepositoryResult implements GithubRepositoryResultData {
    private Long id;
    private String name;
    private TestGithubRepositoryOwner owner;

}
