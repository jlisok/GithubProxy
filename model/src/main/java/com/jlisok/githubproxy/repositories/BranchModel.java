package com.jlisok.githubproxy.repositories;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class BranchModel {
    String name;
    String lastCommitHash;
}
