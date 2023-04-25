package com.jlisok.githubproxy.clients;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.stream.Stream;

@ExtendWith(MockitoExtension.class)
class PaginationUtilTest {
    @InjectMocks
    private PaginationUtil util;

    @ParameterizedTest
    @MethodSource("input")
    void getLastPageNumber(Integer count, Integer size, int expected) {
        //when
        var result = util.getNextPageNumber(count, size);
        //then
        Assertions.assertEquals(expected, result);
    }

    private static Stream<Arguments> input() {
        return Stream.of(
                Arguments.of(0, 30, 1),
                Arguments.of(null, 30, 1),
                Arguments.of(20, 0, 1),
                Arguments.of(20, 10, 2),
                Arguments.of(10, 10, 1),
                Arguments.of(10, 11, 1),
                Arguments.of(11, 10, 2),
                Arguments.of(19, 10, 2)
        );
    }

    @ParameterizedTest
    @MethodSource("inputResponseEntity")
    void getLastPageNumber(ResponseEntity<Integer> response, Integer expected) {
        //when
        var result = util.getNextPageNumber(response);
        //then
        Assertions.assertEquals(expected, result);
    }

    private static Stream<Arguments> inputResponseEntity() {
        return Stream.of(
                Arguments.of(
                        ResponseEntity
                                .noContent()
                                .build(),
                        1
                ),
                Arguments.of(
                        ResponseEntity
                                .noContent()
                                .header("Link",
                                        """
                                                 <https://api.github.com/repositories/28457823/branches?per_page=2&page=2>; rel="last",
                                                 <https://api.github.com/repositories/28457823/branches?per_page=2&page=4>; rel="first"
                                                """
                                )
                                .build(),
                        1
                ),
                Arguments.of(
                        ResponseEntity
                                .noContent()
                                .header("Link",
                                        """
                                                 <https://api.github.com/repositories/28457823/branches?per_page=2&page=4>; rel="next",
                                                 <https://api.github.com/repositories/28457823/branches?per_page=2&page=2>; rel="last"
                                                """
                                )
                                .build(),
                        4
                )
        );
    }

    @ParameterizedTest
    @MethodSource("inputString")
    void getParameter(String url, Integer expected) {
        //given
        var parameterName = "page";
        //when
        var result = util.getParameter(url, parameterName);
        //then
        Assertions.assertEquals(expected, result);
    }

    private static Stream<Arguments> inputString() {
        return Stream.of(
                Arguments.of(
                        "https://api.github.com/repositories/28457823/branches?per_page=1",
                        null
                ),
                Arguments.of(
                        "https://api.github.com/repositories/28457823/branches?per_page=1&page=1",
                        1
                )
        );
    }
}