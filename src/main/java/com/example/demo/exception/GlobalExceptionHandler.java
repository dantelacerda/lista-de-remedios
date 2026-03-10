package com.example.demo.exception;

import com.example.demo.dto.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Tratamento global de exceções (RestControllerAdvice).
 * Erros são padronizados em ResponseEntity com ApiResponse, conforme .cursorrules.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Monta ResponseEntity de erro para uso nos catch dos controllers.
     * Genérico para ser compatível com o tipo de retorno do método (ex.: ApiResponse&lt;List&lt;RemedioDTO&gt;&gt;).
     */
    @SuppressWarnings("unchecked")
    public static <T> ResponseEntity<ApiResponse<T>> errorResponseEntity(String message, HttpStatus status) {
        var response = (ApiResponse<T>) ApiResponse.error(message);
        return new ResponseEntity<>(response, status);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiResponse<?>> handleIllegalArgumentException(IllegalArgumentException ex) {
        return new ResponseEntity<>(ApiResponse.error(ex.getMessage()), HttpStatus.BAD_REQUEST);
    }
}
