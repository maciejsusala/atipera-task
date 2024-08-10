package pl.maciejsusala.atiperatask.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record BranchDTO(
        String name,
        @JsonProperty("commit") CommitDTO commit
) {
    public BranchDTO(String name, String sha) {
        this(name, new CommitDTO(sha));
    }

    public record CommitDTO(String sha) {}
}