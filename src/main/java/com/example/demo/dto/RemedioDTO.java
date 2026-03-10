package com.example.demo.dto;

import java.time.LocalDate;
import java.util.Objects;

/**
 * DTO imutável de Remédio (Java 25/26: Record).
 * Atributos: nome e validade; id presente quando retornado do banco (nullable na entrada).
 *
 * @param id       identificador (null no POST, preenchido na resposta)
 * @param nome     nome do remédio
 * @param validade data de validade
 */
public record RemedioDTO(Long id, String nome, LocalDate validade) {

    /**
     * Construtor compacto: valida nome e validade (não nulos; nome não em branco).
     */
    public RemedioDTO {
        Objects.requireNonNull(nome, "nome must not be null");
        Objects.requireNonNull(validade, "validade must not be null");
        if (nome.isBlank()) {
            throw new IllegalArgumentException("nome must not be blank");
        }
    }

    /**
     * Factory para criação (sem id) — ex.: body do POST.
     */
    public static RemedioDTO of(String nome, LocalDate validade) {
        return new RemedioDTO(null, nome, validade);
    }

    /**
     * Factory para resposta com id — ex.: após salvar ou listar.
     */
    public static RemedioDTO of(Long id, String nome, LocalDate validade) {
        return new RemedioDTO(id, nome, validade);
    }
}
