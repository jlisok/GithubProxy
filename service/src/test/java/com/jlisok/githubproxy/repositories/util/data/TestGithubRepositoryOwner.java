package com.jlisok.githubproxy.repositories.util.data;

import com.jlisok.githubproxy.repositories.contracts.data.GithubRepositoryOwnerData;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TestGithubRepositoryOwner implements GithubRepositoryOwnerData {
    private Long id;
    private String login;
}
