package pl.maciejsusala.atiperatask.dto;

import java.util.List;

public record RepositoryResponseDTO(
        String name,
        String ownerLogin,
        List<BranchDTO> branches
) {}