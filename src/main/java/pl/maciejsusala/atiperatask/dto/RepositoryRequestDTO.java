package pl.maciejsusala.atiperatask.dto;


import jakarta.validation.constraints.NotNull;

public record RepositoryRequestDTO(
        @NotNull String username,
        String acceptHeader
) {}