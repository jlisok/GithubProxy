package com.jlisok.githubproxy.async;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AsyncService {
    private final AsyncProperties properties;
    private final ThreadPoolTaskExecutor executor;

    public <T> Map<String, List<T>> getAllAsync(Function<String, List<T>> function, List<AsyncLinkRequest> parallelParameters) {
        return parallelParameters.stream()
                .map(request -> getAsync(function, request))
                .flatMap(map -> map.entrySet().stream())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    private <T> Map<String, List<T>> getAsync(Function<String, List<T>> function, AsyncLinkRequest request) {
        var futures = request.parameters().stream()
                .map(parameter -> supply(function, parameter))
                .toList();

        return futures.stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    private <T> CompletableFuture<Map.Entry<String, List<T>>> supply(Function<String, List<T>> function, String parameter) {
        return CompletableFuture.supplyAsync(() -> function.apply(parameter), executor)
                .thenApply(result -> Map.entry(parameter, result))
                .orTimeout(properties.getTimeout(), properties.getUnit());
    }
}
