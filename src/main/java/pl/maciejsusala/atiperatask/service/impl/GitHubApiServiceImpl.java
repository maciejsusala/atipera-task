package pl.maciejsusala.atiperatask.service.impl;

import org.springframework.stereotype.Service;
import pl.maciejsusala.atiperatask.client.GitHubApiClient;
import pl.maciejsusala.atiperatask.dto.BranchDTO;
import pl.maciejsusala.atiperatask.dto.RepositoryDTO;
import pl.maciejsusala.atiperatask.dto.RepositoryResponseDTO;
import pl.maciejsusala.atiperatask.service.GitHubApiService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service implementation for interacting with the GitHub API.
 */
@Service
public class GitHubApiServiceImpl implements GitHubApiService {
    private final GitHubApiClient gitHubApiClient;

    public GitHubApiServiceImpl(GitHubApiClient gitHubApiClient) {
        this.gitHubApiClient = gitHubApiClient;
    }

    /**
     * Retrieves a list of non-fork repositories for a given user.
     *
     * @param username the GitHub username
     * @return a list of {@link RepositoryResponseDTO} objects representing non-fork repositories
     */
    @Override
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