package com.jlisok.githubproxy.accounts;

import com.jlisok.githubproxy.repositories.RepositoryModel;
import com.jlisok.githubproxy.users.UserModel;
import lombok.Builder;
import lombok.Value;

import java.util.Collections;
import java.util.List;

@Value
@Builder
public class AccountActivityModel {
    @Builder.Default
    List<RepositoryModel> repositories = Collections.emptyList();
    @Builder.Default
    List<UserModel> users = Collections.emptyList();
}
