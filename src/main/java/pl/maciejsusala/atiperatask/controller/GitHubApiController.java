package pl.maciejsusala.atiperatask.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.maciejsusala.atiperatask.dto.RepositoryRequestDTO;
import pl.maciejsusala.atiperatask.dto.RepositoryResponseDTO;
import pl.maciejsusala.atiperatask.service.impl.GitHubApiServiceImpl;

import java.util.List;

@RestController
@RequestMapping("api/v1/github")
public class GitHubApiController {
    private final GitHubApiServiceImpl gitHubService;

    public GitHubApiController(GitHubApiServiceImpl gitHubService) {
        this.gitHubService = gitHubService;
    }

    @PostMapping("/users/repos")
    public ResponseEntity<List<RepositoryResponseDTO>> getReposNotFork(
            @RequestBody RepositoryRequestDTO repositoryRequest,
            @RequestHeader(value = "Accept", required = true) String acceptHeader) {
        List<RepositoryResponseDTO> nonForkRepos = gitHubService
                .getNonForkRepositories(repositoryRequest.username());
        return ResponseEntity.ok(nonForkRepos);
    }
}