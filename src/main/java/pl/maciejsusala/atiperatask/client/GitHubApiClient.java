package pl.maciejsusala.atiperatask.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pl.maciejsusala.atiperatask.config.FeignConfig;
import pl.maciejsusala.atiperatask.dto.BranchDTO;
import pl.maciejsusala.atiperatask.dto.RepositoryDTO;

import java.util.List;

/**
 * Feign client for interacting with the GitHub API.
 */
@FeignClient(name = "github-api-client", url = "https://api.github.com", configuration = FeignConfig.class)
public interface GitHubApiClient {

    /**
     * Retrieves a list of repositories for a given user.
     *
     * @param username the GitHub username
     * @return a list of {@link RepositoryDTO} objects
     */
    @RequestMapping(method = RequestMethod.GET, value = "/users/{username}/repos")
    List<RepositoryDTO> getRepositories(@PathVariable("username") String username);

    /**
     * Retrieves a list of branches for a given repository.
     *
     * @param owner the owner of the repository
     * @param repo the name of the repository
     * @return a list of {@link BranchDTO} objects
     */
    @RequestMapping(method = RequestMethod.GET, value = "/repos/{owner}/{repo}/branches")
    List<BranchDTO> getBranches(@PathVariable("owner") String owner, @PathVariable("repo") String repo);
}