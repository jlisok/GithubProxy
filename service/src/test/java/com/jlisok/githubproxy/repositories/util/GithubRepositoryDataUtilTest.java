package com.jlisok.githubproxy.repositories.util;

import com.jlisok.githubproxy.repositories.constracts.GithubRepositoryResultData;
import com.jlisok.githubproxy.repositories.util.data.TestGithubRepositoryResult;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

@ExtendWith(MockitoExtension.class)
class GithubRepositoryDataUtilTest {
    @InjectMocks
    private GithubRepositoryDataUtil util;

    @ParameterizedTest
    @MethodSource("input")
    void extractRepositoryNames(List<GithubRepositoryResultData> repositories, List<String> expected) {
        //when
        var result = util.extractRepositoryNames(repositories);
        //then
        Assertions.assertEquals(expected, result);
    }

    private static Stream<Arguments> input() {
        return Stream.of(
                Arguments.of(
                        null,
                        Collections.emptyList()
                ),
                Arguments.of(
                        Collections.emptyList(),
                        Collections.emptyList()
                ),
                Arguments.of(
                        List.of(
                                TestGithubRepositoryResult.builder().build()
                        ),
                        Collections.emptyList()
                ),
                Arguments.of(
                        List.of(
                                TestGithubRepositoryResult.builder().name("").build()
                        ),
                        Collections.emptyList()
                ),
                Arguments.of(
                        List.of(
                                TestGithubRepositoryResult.builder().name(" ").build()
                        ),
                        Collections.emptyList()
                ),
                Arguments.of(
                        List.of(
                                TestGithubRepositoryResult.builder().name("SOME_STRING").build()
                        ),
                        List.of("SOME_STRING")
                )
        );
    }
}