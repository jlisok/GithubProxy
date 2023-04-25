package com.jlisok.githubproxy.repositories;

import com.jlisok.githubproxy.repositories.constracts.GithubRepositoryResultData;
import lombok.Data;

@Data
public class GithubRepositoryResult implements GithubRepositoryResultData {
    private Long id;
    private String name;
    private GithubRepositoryOwner owner;

}
