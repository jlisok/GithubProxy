package com.jlisok.githubproxy.repositories;

import com.jlisok.githubproxy.repositories.contracts.data.GithubRepositoryResultData;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GithubRepositoryResult implements GithubRepositoryResultData {
    private Long id;
    private String name;
    private GithubRepositoryOwner owner;

}
