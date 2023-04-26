package com.jlisok.githubproxy;

import com.jlisok.githubproxy.accounts.AccountActivityModel;
import com.jlisok.githubproxy.facades.GithubAccountFacade;
import com.jlisok.githubproxy.pagination.PaginationModel;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.jlisok.githubproxy.accounts.AccountActivityUris.USER_ACCOUNT_ACTIVITY;

@RestController
@RequiredArgsConstructor
public class AccountActivityController {
    private final GithubAccountFacade facade;

    @GetMapping(
            path = USER_ACCOUNT_ACTIVITY,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public PaginationModel<AccountActivityModel> getAccountActivity(@NonNull @PathVariable String name,
                                                                    @RequestParam(required = false) Integer pageNumber) {

        return facade.getAccountActivity(name, pageNumber);
    }
}
