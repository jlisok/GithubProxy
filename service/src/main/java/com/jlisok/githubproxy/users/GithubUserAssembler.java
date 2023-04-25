package com.jlisok.githubproxy.users;

import com.jlisok.githubproxy.repositories.constracts.GithubRepositoryOwnerData;
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
