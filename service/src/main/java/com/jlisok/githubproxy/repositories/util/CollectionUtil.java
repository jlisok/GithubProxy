package com.jlisok.githubproxy.repositories.util;

import lombok.experimental.UtilityClass;

import java.util.*;
import java.util.function.Function;

@UtilityClass
public class CollectionUtil {

    public <R, T> List<T> toList(Collection<R> collection, Function<R, T> function) {
        return Optional.ofNullable(collection)
                .orElse(Collections.emptyList()).stream()
                .map(function)
                .filter(Objects::nonNull)
                .toList();
    }
}
