package com.jlisok.githubproxy.pagination;

public record PaginationModel<T>(boolean hasNext, int pageNumber, T data) {
}
