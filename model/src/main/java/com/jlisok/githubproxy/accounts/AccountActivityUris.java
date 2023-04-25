package com.jlisok.githubproxy.accounts;

import lombok.experimental.UtilityClass;

@UtilityClass
public class AccountActivityUris {
    private static final String BASE_URL = "/users";
    private static final String NAME_PARAM = "/{name}";

    public static final String USER_ACCOUNT_ACTIVITY = BASE_URL + NAME_PARAM + "/account-activity";
}
