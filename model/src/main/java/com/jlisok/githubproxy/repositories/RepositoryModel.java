package com.jlisok.githubproxy.repositories;

import lombok.Builder;
import lombok.Value;

import java.util.Collections;
import java.util.List;

@Value
@Builder
public class RepositoryModel {
    String name;
    Long ownerId;
    @Builder.Default
    List<BranchModel> branches = Collections.emptyList();
}
