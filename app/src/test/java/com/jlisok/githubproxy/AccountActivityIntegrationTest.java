package com.jlisok.githubproxy;

import com.jlisok.githubproxy.exceptions.ExceptionMessageConstants;
import com.jlisok.githubproxy.mock.GithubStubMock;
import org.apache.commons.lang3.RandomStringUtils;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static com.jlisok.githubproxy.accounts.AccountActivityUris.USER_ACCOUNT_ACTIVITY;
import static com.jlisok.githubproxy.exceptions.ExceptionCode.*;
import static com.jlisok.githubproxy.exceptions.ExceptionMessageConstants.GITHUB_UNAVAILABLE;
import static com.jlisok.githubproxy.exceptions.ExceptionMessageConstants.REPOSITORIES_NOT_FOUND;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureWireMock(port = 9099)
class AccountActivityIntegrationTest {
    @Autowired
    private MockMvc mvc;
    @Autowired
    private GithubStubMock stub;

    @Test
    void getAccountActivity_exampleIntegrationTest() throws Exception {
        //given
        var userName = RandomStringUtils.randomAlphabetic(10);

        var testRepositoryResult = stub.stubRepositoryResponse();
        var repository = testRepositoryResult.getRepositories().get(0);
        var testBranch = stub.stubBranchResponse(repository.getName(), userName);

        //when // then
        mvc.perform(MockMvcRequestBuilders.get(USER_ACCOUNT_ACTIVITY, userName)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("hasNext", Matchers.equalTo(false)))
                .andExpect(jsonPath("pageNumber", Matchers.equalTo(1)))

                .andExpect(jsonPath("data.repositories", Matchers.hasSize(1)))
                .andExpect(jsonPath("data.repositories[0].name", Matchers.equalTo(repository.getName())))
                .andExpect(jsonPath("data.repositories[0].ownerId", Matchers.equalTo(repository.getOwner().getId())))

                .andExpect(jsonPath("data.repositories[0].branches", Matchers.hasSize(1)))
                .andExpect(jsonPath("data.repositories[0].branches[0].name", Matchers.equalTo(testBranch.getName())))
                .andExpect(jsonPath("data.repositories[0].branches[0].lastCommitHash", Matchers.equalTo(testBranch.getGithubCommitData().getSha())))

                .andExpect(jsonPath("data.users", Matchers.hasSize(1)))
                .andExpect(jsonPath("data.users[0].id", Matchers.equalTo(repository.getOwner().getId())))
                .andExpect(jsonPath("data.users[0].login", Matchers.equalTo(repository.getOwner().getLogin())));
    }

    @Test
    void getAccountActivity_stub503() throws Exception {
        //given
        var userName = RandomStringUtils.randomAlphabetic(10);

        stub.stubRepositoryResponseStatus(HttpStatus.SERVICE_UNAVAILABLE);

        //when // then
        mvc.perform(MockMvcRequestBuilders.get(USER_ACCOUNT_ACTIVITY, userName).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isServiceUnavailable())
                .andExpect(jsonPath("code", Matchers.equalTo(GITHUB_API_EXCEPTION.toString())))
                .andExpect(jsonPath("message", Matchers.equalTo(GITHUB_UNAVAILABLE)));
    }

    @Test
    void getAccountActivity_stub404() throws Exception {
        //given
        var userName = RandomStringUtils.randomAlphabetic(10);

        stub.stubRepositoryResponseStatus(HttpStatus.NOT_FOUND);

        //when // then
        mvc.perform(MockMvcRequestBuilders.get(USER_ACCOUNT_ACTIVITY, userName).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("code", Matchers.equalTo(OBJECT_NOT_FOUND.toString())))
                .andExpect(jsonPath("message", Matchers.equalTo(REPOSITORIES_NOT_FOUND)));
    }

    @Test
    void getAccountActivity_stub400() throws Exception {
        //given
        var userName = RandomStringUtils.randomAlphabetic(10);

        stub.stubRepositoryResponseStatus(HttpStatus.BAD_REQUEST);

        //when // then
        mvc.perform(MockMvcRequestBuilders.get(USER_ACCOUNT_ACTIVITY, userName).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("code", Matchers.equalTo(BAD_REQUEST.toString())))
                .andExpect(jsonPath("message", Matchers.equalTo(ExceptionMessageConstants.BAD_REQUEST)));
    }
}
