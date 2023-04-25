package com.jlisok.githubproxy.pagination;

public record PagedData<T>(T getData, int currentPageNumber, int lastPageNumber, boolean hasNext) {
}
