package com.jlisok.githubproxy.githubbranches;

import com.jlisok.githubproxy.async.AsyncRequestUtil;
import com.jlisok.githubproxy.async.AsyncService;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class GithubBranchService {
    private final AsyncService asyncService;
    private final GithubBranchRepository repository;
    private final GithubBranchAssembler assembler;
    private final AsyncRequestUtil asyncRequestUtil;

    public Map<String, List<GithubBranchData>> getAsyncGithubBranchesInRepositories(@NonNull String owner,
                                                                                    @NonNull List<String> repositoryNames) {

        var asyncRequest = asyncRequestUtil.partition(repositoryNames);
        return asyncService.getAllAsync(repoName -> getGithubBranches(owner, repoName), asyncRequest);
    }

    public List<GithubBranchData> getGithubBranches(@NonNull String owner, @NonNull String repositoryName) {
        var request = assembler.toGithubBranchRequest(owner, repositoryName);
        return repository.getGithubBranchData(request);
    }

}
