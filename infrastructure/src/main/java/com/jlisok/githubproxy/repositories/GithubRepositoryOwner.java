package com.jlisok.githubproxy.repositories;

import com.jlisok.githubproxy.repositories.contracts.data.GithubRepositoryOwnerData;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GithubRepositoryOwner implements GithubRepositoryOwnerData {
    private Long id;
    private String login;
}
