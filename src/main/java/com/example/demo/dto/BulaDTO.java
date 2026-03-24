package com.example.demo.dto;

import java.time.LocalDate;
import java.util.Objects;

/**
 * DTO imutável de Bula (Java 25/26: Record).
 * Atributos: nome e validade; id presente quando retornado do banco (nullable na entrada).
 *
 * @param id       identificador (null no POST, preenchido na resposta)
 * @param nome     nome da bula
 * @param validade data de validade
 */
public record BulaDTO(Long id, String nome, LocalDate validade) {

    /**
     * Construtor compacto: valida nome e validade (não nulos; nome não em branco).
     */
    public BulaDTO {
        Objects.requireNonNull(nome, "nome must not be null");
        Objects.requireNonNull(validade, "validade must not be null");
        if (nome.isBlank()) {
            throw new IllegalArgumentException("nome must not be blank");
        }
    }

    /**
     * Factory para criação (sem id) — ex.: body do POST.
     */
    public static BulaDTO of(String nome, LocalDate validade) {
        return new BulaDTO(null, nome, validade);
    }

    /**
     * Factory para resposta com id — ex.: após salvar ou listar.
     */
    public static BulaDTO of(Long id, String nome, LocalDate validade) {
        return new BulaDTO(id, nome, validade);
    }
}

