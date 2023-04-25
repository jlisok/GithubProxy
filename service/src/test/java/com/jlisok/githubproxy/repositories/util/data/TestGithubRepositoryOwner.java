package com.jlisok.githubproxy.repositories.util.data;

import com.jlisok.githubproxy.repositories.constracts.GithubRepositoryOwnerData;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TestGithubRepositoryOwner implements GithubRepositoryOwnerData {
    private Long id;
    private String login;
}
