package com.jlisok.githubproxy.repositories.assemblers;

import com.jlisok.githubproxy.repositories.pojo.GithubRepositoryConstants;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@ExtendWith(MockitoExtension.class)
class GithubRepositoryAssemblerTest {
    @InjectMocks
    private GithubRepositoryAssembler assembler;

    @Test
    void toSearchCriteriaParam() {
        //given
        var name = RandomStringUtils.randomAlphabetic(10);
        var expected = GithubRepositoryConstants.SearchCriteria.USER_NAME_KEY + ":" + name;
        //when
        var result = assembler.toSearchCriteriaParam(name);
        //then
        Assertions.assertEquals(URLEncoder.encode(expected, StandardCharsets.UTF_8), result);
    }

    @Test
    void toGithubRepositoryRequest() {
        //given
        var pageNumber = RandomUtils.nextInt();
        //when
        var result = assembler.toGithubRepositoryRequest(RandomStringUtils.randomAlphabetic(10), pageNumber);
        //then
        Assertions.assertNotNull(result);
        Assertions.assertEquals(pageNumber, result.getPageNumber());
        Assertions.assertEquals(GithubRepositoryConstants.SearchCriteria.PAGE_SIZE, result.getPageSize());
        Assertions.assertEquals(GithubRepositoryConstants.SearchCriteria.FORK_TYPE, result.isForkType());
    }
}