package com.jlisok.githubproxy.repositories;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jlisok.githubproxy.repositories.constracts.GithubRepositoryResultData;
import com.jlisok.githubproxy.repositories.constracts.GithubRepositorySearchData;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GithubRepositorySearchResult implements GithubRepositorySearchData {
    @JsonProperty(value = "total_count")
    private Integer totalCount;
    @JsonProperty(value = "incomplete_results")
    private Boolean incompleteResults;
    @JsonProperty(value = "items")
    private List<GithubRepositoryResult> repositories;

    @Override
    public List<GithubRepositoryResultData> getRepositoryData() {
        return new ArrayList<>(repositories);
    }
}
