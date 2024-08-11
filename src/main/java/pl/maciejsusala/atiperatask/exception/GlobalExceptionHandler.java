package pl.maciejsusala.atiperatask.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import pl.maciejsusala.atiperatask.dto.ErrorResponseDto;

/**
 * Global exception handler for handling custom exceptions in the application.
 */
@RestControllerAdvice
public class GlobalExceptionHandler extends RuntimeException {

    /**
     * Handles CustomFeignException and returns an appropriate error response.
     *
     * @param ex the CustomFeignException thrown
     * @return a ResponseEntity containing an ErrorResponseDto with the error details
     */
    @ExceptionHandler(CustomFeignException.class)
    public ResponseEntity<ErrorResponseDto> handleCustomFeignException(CustomFeignException ex) {
        return new ResponseEntity<>(
                new ErrorResponseDto(ex.status(), ex.getMessage()),
                HttpStatus.valueOf(ex.status())
        );
    }


}