package com.jlisok.githubproxy.githubbranches;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.jlisok.githubproxy.repositories.pojo.GithubRepositoryConstants.SearchCriteria.DEFAULT_PAGE_NUMBER;
import static com.jlisok.githubproxy.repositories.pojo.GithubRepositoryConstants.SearchCriteria.PAGE_SIZE;

@ExtendWith(MockitoExtension.class)
class GithubBranchAssemblerTest {
    @InjectMocks
    private GithubBranchAssembler assembler;

    @Test
    void toGithubBranchRequest() {
        //given
        var owner = RandomStringUtils.randomAlphabetic(10);
        var repository = RandomStringUtils.randomAlphabetic(10);
        //when
        var result = assembler.toGithubBranchRequest(owner, repository);
        //then
        Assertions.assertEquals(owner, result.getUsername());
        Assertions.assertEquals(repository, result.getRepositoryName());
        Assertions.assertEquals(DEFAULT_PAGE_NUMBER, result.getPageNumber());
        Assertions.assertEquals(PAGE_SIZE, result.getPageSize());
    }
}