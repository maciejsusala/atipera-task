package pl.maciejsusala.atiperatask.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pl.maciejsusala.atiperatask.client.GitHubApiClient;
import pl.maciejsusala.atiperatask.dto.BranchDTO;
import pl.maciejsusala.atiperatask.dto.RepositoryDTO;
import pl.maciejsusala.atiperatask.dto.RepositoryResponseDTO;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class GitHubApiServiceImplTest {

    @Mock
    private GitHubApiClient gitHubApiClient;

    @InjectMocks
    private GitHubApiServiceImpl gitHubApiService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetNonForkRepositories() {
        String username = "maciejsusala";
        List<RepositoryDTO> repositories = List.of(
                new RepositoryDTO("repo1", new RepositoryDTO.OwnerDTO("maciejsusala"), false, null),
                new RepositoryDTO("repo2", new RepositoryDTO.OwnerDTO("maciejsusala"), true, null)
        );
        List<BranchDTO> branches = List.of(
                new BranchDTO("main", "sha1"),
                new BranchDTO("dev", "sha2")
        );

        when(gitHubApiClient.getRepositories(username)).thenReturn(repositories);
        when(gitHubApiClient.getBranches("maciejsusala", "repo1")).thenReturn(branches);

        List<RepositoryResponseDTO> result = gitHubApiService.getNonForkRepositories(username);

        assertEquals(1, result.size());
        assertEquals("repo1", result.getFirst().name());
        assertEquals("maciejsusala", result.getFirst().ownerLogin());
        assertEquals(2, result.getFirst().branches().size());
        assertEquals("main", result.getFirst().branches().getFirst().name());
        assertEquals("sha1", result.getFirst().branches().getFirst().commit().sha());
        assertEquals("dev", result.getFirst().branches().get(1).name());
        assertEquals("sha2", result.getFirst().branches().get(1).commit().sha());
    }
}