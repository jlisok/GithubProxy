package com.jlisok.githubproxy.async;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static org.mockito.Mockito.lenient;

@ExtendWith(MockitoExtension.class)
class AsyncRequestUtilTest {
    @Mock
    private AsyncProperties properties;
    @InjectMocks
    private AsyncRequestUtil util;

    @ParameterizedTest
    @MethodSource("input")
    void partition(List<String> names, List<AsyncLinkRequest> expected) {
        //given
        lenient().when(properties.getMaxThreadPoolSize()).thenReturn(2);
        //when
        var result = util.partition(names);
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
                        List.of("ONE"),
                        List.of(
                                new AsyncLinkRequest(List.of("ONE"))
                        )
                ),
                Arguments.of(
                        List.of(
                                "ONE",
                                "TWO",
                                "THREE"
                        ),
                        List.of(
                                new AsyncLinkRequest(List.of("ONE", "TWO")),
                                new AsyncLinkRequest(List.of("THREE"))
                        )
                )
        );
    }
}