package com.jlisok.githubproxy.async;

import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.ListUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;

@Component
@RequiredArgsConstructor
public class AsyncRequestUtil {
    private final AsyncProperties properties;

    public List<AsyncLinkRequest> partition(List<String> names) {
        if (CollectionUtils.isEmpty(names)) return Collections.emptyList();

        var size = properties.getMaxThreadPoolSize();
        if (size < 1) return Collections.emptyList();

        return ListUtils.partition(names, properties.getMaxThreadPoolSize()).stream()
                .map(AsyncLinkRequest::new)
                .toList();
    }
}
