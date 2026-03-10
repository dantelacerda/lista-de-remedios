package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Entidade JPA para remédio (persistência em H2).
 * Usada apenas para carregar/gravar dados no banco; uso de DTOs na API (regras .cursorrules).
 * Sem Lombok: classe com getters/setters para compatibilidade com JPA.
 */
@Entity
@Table(name = "remedios")
public class Remedio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false, length = 255)
    private String nome;

    @NotNull
    @Column(nullable = false)
    private LocalDate validade;

    /** Construtor sem argumentos exigido pelo JPA. */
    public Remedio() {
    }

    public Remedio(String nome, LocalDate validade) {
        this.nome = nome;
        this.validade = validade;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getValidade() {
        return validade;
    }

    public void setValidade(LocalDate validade) {
        this.validade = validade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Remedio remedio = (Remedio) o;
        return Objects.equals(id, remedio.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
