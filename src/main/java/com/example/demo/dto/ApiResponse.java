package com.example.demo.dto;

import java.util.Objects;

/**
 * DTO imutável para respostas da API (Java 25/26: Record em vez de classe + Lombok).
 * Records garantem imutabilidade por padrão e concisão.
 *
 * @param result  "SUCCESS" ou "ERROR"
 * @param message mensagem de sucesso ou erro
 * @param data    payload retornado pelo serviço; null em caso de erro
 */
public record ApiResponse<T>(String result, String message, T data) {

    /**
     * Construtor compacto: validação dos parâmetros (boa prática para DTOs em Records).
     * result não pode ser nulo nem em branco; message não pode ser nulo (em branco permitido para erro).
     */
    public ApiResponse {
        Objects.requireNonNull(result, "result must not be null");
        Objects.requireNonNull(message, "message must not be null");
        if (result.isBlank()) {
            throw new IllegalArgumentException("result must not be blank");
        }
    }

    /**
     * Factory para resposta de sucesso (imutabilidade: sempre criamos novo record).
     */
    public static <T> ApiResponse<T> success(String message, T data) {
        return new ApiResponse<>("SUCCESS", message, data);
    }

    /**
     * Factory para resposta de erro (data é null).
     * Trata message nula (ex.: ex.getMessage()) usando valor padrão.
     */
    public static <T> ApiResponse<T> error(String message) {
        return new ApiResponse<>("ERROR", message != null ? message : "Unknown error", null);
    }
}
