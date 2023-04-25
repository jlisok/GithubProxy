package com.jlisok.githubproxy.users;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class UserModel {
    Long id;
    String login;
}
