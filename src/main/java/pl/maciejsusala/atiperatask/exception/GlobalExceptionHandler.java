package pl.maciejsusala.atiperatask.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import pl.maciejsusala.atiperatask.dto.ErrorResponseDto;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomFeignException.class)
    public ResponseEntity<ErrorResponseDto> handleCustomFeignException(CustomFeignException ex) {
        return new ResponseEntity<>(
                new ErrorResponseDto(ex.status(), ex.getMessage()),
                HttpStatus.valueOf(ex.status())
        );
    }


}