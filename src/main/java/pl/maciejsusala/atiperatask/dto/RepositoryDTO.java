package pl.maciejsusala.atiperatask.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record RepositoryDTO(
        String name,
        @JsonProperty("owner") OwnerDTO owner,
        @JsonProperty("fork") boolean isFork,
        List<BranchDTO> branches
) {
    public record OwnerDTO(String login) {}
}