package pl.maciejsusala.atiperatask.service.impl;

import org.springframework.stereotype.Service;
import pl.maciejsusala.atiperatask.client.GitHubApiClient;
import pl.maciejsusala.atiperatask.dto.BranchDTO;
import pl.maciejsusala.atiperatask.dto.RepositoryDTO;
import pl.maciejsusala.atiperatask.dto.RepositoryResponseDTO;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GitHubApiServiceImpl {
    private final GitHubApiClient gitHubApiClient;

    public GitHubApiServiceImpl(GitHubApiClient gitHubApiClient) {
        this.gitHubApiClient = gitHubApiClient;
    }

    public List<RepositoryResponseDTO> getNonForkRepositories(String username) {
        List<RepositoryDTO> repositories = gitHubApiClient.getRepositories(username);



        return repositories.stream()
                .filter(repo -> !repo.isFork())
                .map(repo -> {
                    List<BranchDTO> branches = gitHubApiClient.getBranches(repo.owner().login(), repo.name()).stream()
                            .map(branch -> new BranchDTO(branch.name(), branch.commit().sha()))
                            .collect(Collectors.toList());
                    return new RepositoryResponseDTO(repo.name(), repo.owner().login(), branches);
                })
                .collect(Collectors.toList());
    }
}