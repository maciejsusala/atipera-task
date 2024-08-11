package pl.maciejsusala.atiperatask.dto;

public record ErrorResponseDto(
        int status,
        String message
) {}