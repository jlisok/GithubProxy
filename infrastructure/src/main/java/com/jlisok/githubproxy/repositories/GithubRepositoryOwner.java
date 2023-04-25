package com.jlisok.githubproxy.repositories;

import com.jlisok.githubproxy.repositories.constracts.GithubRepositoryOwnerData;
import lombok.Data;

@Data
public class GithubRepositoryOwner implements GithubRepositoryOwnerData {
    private Long id;
    private String login;
}
