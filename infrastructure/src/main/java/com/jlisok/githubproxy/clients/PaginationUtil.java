package com.jlisok.githubproxy.clients;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Objects;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.jlisok.githubproxy.clients.GithubClientConstants.PAGE_NUMBER_NAME;

@Component
public class PaginationUtil {
    private static final String NEXT_PAGE = "rel=\"next\"";
    private static final Pattern URL_PATTERN = Pattern.compile("(?<=<)([\\S]*)(?=>; rel=\"next\")", Pattern.CASE_INSENSITIVE);

    public <T> Integer getNextPageNumber(ResponseEntity<T> response) {
        return Optional.ofNullable(response)
                .map(HttpEntity::getHeaders)
                .map(item -> item.get(HttpHeaders.LINK))
                .orElseGet(Collections::emptyList).stream()
                .filter(item -> item.contains(NEXT_PAGE))
                .map(URL_PATTERN::matcher)
                .filter(Matcher::find)
                .map(Matcher::group)
                .map(item -> getParameter(item, PAGE_NUMBER_NAME))
                .filter(Objects::nonNull)
                .findFirst()
                .orElse(1);
    }


    Integer getParameter(@NonNull String urlString, @NonNull String parameterName) {
        var uri = URI.create(urlString);

        return URLEncodedUtils.parse(uri, StandardCharsets.UTF_8).stream()
                .filter(item -> parameterName.equals(item.getName()))
                .map(NameValuePair::getValue)
                .filter(StringUtils::isNumeric)
                .map(Integer::valueOf)
                .findFirst()
                .orElse(null);
    }

    public Integer getNextPageNumber(Integer totalCount, Integer pageSize) {
        if (Objects.isNull(totalCount) || totalCount < 1) return 1;

        var totalCountDecimal = BigDecimal.valueOf(totalCount);
        var pageSizeDecimal = Optional.ofNullable(pageSize)
                .map(BigDecimal::valueOf)
                .filter(item -> !BigDecimal.ZERO.equals(item))
                .orElse(totalCountDecimal);

        return totalCountDecimal
                .divide(pageSizeDecimal, 0, RoundingMode.CEILING)
                .intValue();
    }
}
