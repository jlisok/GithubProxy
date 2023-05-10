package com.jlisok.githubproxy.users;

import com.jlisok.githubproxy.repositories.contracts.data.GithubRepositoryOwnerData;
import org.springframework.stereotype.Component;

@Component
public class GithubUserAssembler {

    public UserModel toUserModel(GithubRepositoryOwnerData owner) {
        return UserModel.builder()
                .id(owner.getId())
                .login(owner.getLogin())
                .build();
    }
}
