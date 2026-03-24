package com.example.demo.dto;

import java.time.LocalDate;
import java.util.Objects;

/**
 * DTO imutável de Bula (Java 25/26: Record).
 * Atributos: nome, validade, fabricante e dosagem; id presente quando retornado do banco (nullable na entrada).
 *
 * @param id         identificador (null no POST, preenchido na resposta)
 * @param nome       nome da bula
 * @param validade   data de validade
 * @param fabricante nome do fabricante
 * @param dosagem    dosagem do medicamento
 */
public record BulaDTO(Long id, String nome, LocalDate validade, String fabricante, Double dosagem) {

    /**
     * Construtor compacto: valida nome, validade, fabricante e dosagem (não nulos; nome e fabricante não em branco).
     */
    public BulaDTO {
        Objects.requireNonNull(nome, "nome must not be null");
        Objects.requireNonNull(validade, "validade must not be null");
        Objects.requireNonNull(fabricante, "fabricante must not be null");
        Objects.requireNonNull(dosagem, "dosagem must not be null");
        if (nome.isBlank()) {
            throw new IllegalArgumentException("nome must not be blank");
        }
        if (fabricante.isBlank()) {
            throw new IllegalArgumentException("fabricante must not be blank");
        }
    }

    /**
     * Factory para criação (sem id) — ex.: body do POST.
     */
    public static BulaDTO of(String nome, LocalDate validade, String fabricante, Double dosagem) {
        return new BulaDTO(null, nome, validade, fabricante, dosagem);
    }

    /**
     * Factory para resposta com id — ex.: após salvar ou listar.
     */
    public static BulaDTO of(Long id, String nome, LocalDate validade, String fabricante, Double dosagem) {
        return new BulaDTO(id, nome, validade, fabricante, dosagem);
    }
}

