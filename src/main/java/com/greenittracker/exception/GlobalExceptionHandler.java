package com.greenittracker.exception;

import com.greenittracker.dto.response.ErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleResourceNotFound(
            ResourceNotFoundException ex) {

        ErrorResponseDto response = ErrorResponseDto.builder()
                .success(false)
                .message(ex.getMessage())
                .status(HttpStatus.NOT_FOUND.value())
                .timestamp(LocalDateTime.now())
                .build();

        return new ResponseEntity<>(
                response,
                HttpStatus.NOT_FOUND
        );
    }
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleUserNotFound(
            UserNotFoundException ex) {

        ErrorResponseDto response = ErrorResponseDto.builder()
                .success(false)
                .message(ex.getMessage())
                .status(HttpStatus.NOT_FOUND.value())
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(response);
    }
    @ExceptionHandler(DuplicateResourceException.class)
    public ResponseEntity<ErrorResponseDto> handleDuplicateResource(
            DuplicateResourceException ex) {

        ErrorResponseDto response = ErrorResponseDto.builder()
                .success(false)
                .message(ex.getMessage())
                .status(HttpStatus.CONFLICT.value())
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(response);
    }
    @ExceptionHandler(DuplicateUserException.class)
    public ResponseEntity<ErrorResponseDto> handleDuplicateUser(
            DuplicateUserException ex) {

        ErrorResponseDto response = ErrorResponseDto.builder()
                .success(false)
                .message(ex.getMessage())
                .status(HttpStatus.CONFLICT.value())
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(response);
    }
    @ExceptionHandler(InvalidResourceStateException.class)
    public ResponseEntity<ErrorResponseDto> handleInvalidResourceState(
            InvalidResourceStateException ex) {

        ErrorResponseDto response = ErrorResponseDto.builder()
                .success(false)
                .message(ex.getMessage())
                .status(HttpStatus.BAD_REQUEST.value())
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(response);
    }
    @ExceptionHandler(UnauthorizedAccessException.class)
    public ResponseEntity<ErrorResponseDto> handleUnauthorized(
            UnauthorizedAccessException ex) {

        ErrorResponseDto response = ErrorResponseDto.builder()
                .success(false)
                .message(ex.getMessage())
                .status(HttpStatus.UNAUTHORIZED.value())
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(response);
    }
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorResponseDto> handleBadRequest(
            BadRequestException ex) {

        ErrorResponseDto response = ErrorResponseDto.builder()
                .success(false)
                .message(ex.getMessage())
                .status(HttpStatus.BAD_REQUEST.value())
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(response);
    }
    @ExceptionHandler(RecommendationGenerationException.class)
    public ResponseEntity<ErrorResponseDto> handleRecommendationException(
            RecommendationGenerationException ex) {

        ErrorResponseDto response = ErrorResponseDto.builder()
                .success(false)
                .message(ex.getMessage())
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(response);
    }
    @ExceptionHandler(SustainabilityCalculationException.class)
    public ResponseEntity<ErrorResponseDto> handleSustainabilityException(
            SustainabilityCalculationException ex) {

        ErrorResponseDto response = ErrorResponseDto.builder()
                .success(false)
                .message(ex.getMessage())
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(response);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDto> handleValidationErrors(
            MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult()
                .getAllErrors()
                .forEach(error -> {

                    String fieldName =
                            ((FieldError) error).getField();

                    String errorMessage =
                            error.getDefaultMessage();

                    errors.put(fieldName, errorMessage);
                });

        ErrorResponseDto response = ErrorResponseDto.builder()
                .success(false)
                .message("Validation Failed")
                .status(HttpStatus.BAD_REQUEST.value())
                .timestamp(LocalDateTime.now())
                .validationErrors(errors)
                .build();

        return ResponseEntity.badRequest()
                .body(response);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> handleGenericException(
            Exception ex) {

        ErrorResponseDto response = ErrorResponseDto.builder()
                .success(false)
                .message(ex.getMessage())
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.status(
                HttpStatus.INTERNAL_SERVER_ERROR
        ).body(response);
    }
}



