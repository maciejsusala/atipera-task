package pl.maciejsusala.atiperatask.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.maciejsusala.atiperatask.dto.RepositoryRequestDTO;
import pl.maciejsusala.atiperatask.dto.RepositoryResponseDTO;
import pl.maciejsusala.atiperatask.service.GitHubApiService;

import java.util.List;

/**
 * REST controller for interacting with the GitHub API.
 */
@RestController
@RequestMapping("api/v1/github")
public class GitHubApiController {
    private final GitHubApiService gitHubService;

    public GitHubApiController(GitHubApiService gitHubService) {
        this.gitHubService = gitHubService;
    }

    /**
     * Retrieves a list of non-fork repositories for a given user.
     *
     * @param repositoryRequest the request containing the GitHub username
     * @param acceptHeader the Accept header value
     * @return a ResponseEntity containing a list of {@link RepositoryResponseDTO} objects
     */
    @PostMapping("/users/repos")
    public ResponseEntity<List<RepositoryResponseDTO>> getReposNotFork(
            @RequestBody RepositoryRequestDTO repositoryRequest,
            @RequestHeader(value = "Accept") String acceptHeader) {
        List<RepositoryResponseDTO> nonForkRepos = gitHubService
                .getNonForkRepositories(repositoryRequest.username());
        return ResponseEntity.ok(nonForkRepos);
    }
}