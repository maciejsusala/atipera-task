package pl.maciejsusala.atiperatask.service;

import pl.maciejsusala.atiperatask.dto.RepositoryResponseDTO;

import java.util.List;

public interface GitHubApiService {
    List<RepositoryResponseDTO> getNonForkRepositories(String username);
}