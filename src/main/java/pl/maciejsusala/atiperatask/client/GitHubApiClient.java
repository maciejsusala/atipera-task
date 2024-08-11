package pl.maciejsusala.atiperatask.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pl.maciejsusala.atiperatask.config.FeignConfig;
import pl.maciejsusala.atiperatask.dto.BranchDTO;
import pl.maciejsusala.atiperatask.dto.RepositoryDTO;

import java.util.List;

@FeignClient(name = "github-api-client", url = "https://api.github.com", configuration = FeignConfig.class)
public interface GitHubApiClient {

    @RequestMapping(method = RequestMethod.GET, value = "/users/{username}/repos")
    List<RepositoryDTO> getRepositories(@PathVariable("username") String username);

    @RequestMapping(method = RequestMethod.GET, value = "/repos/{owner}/{repo}/branches")
    List<BranchDTO> getBranches(@PathVariable("owner") String owner, @PathVariable("repo") String repo);
}
