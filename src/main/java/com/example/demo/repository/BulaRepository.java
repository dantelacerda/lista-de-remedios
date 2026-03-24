package com.example.demo.repository;

import com.example.demo.entity.Bula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repositório JPA para Bula (apenas interface; Spring Data implementa).
 */
@Repository
public interface BulaRepository extends JpaRepository<Bula, Long> {

    /**
     * Lista bulas cujo nome contém o texto informado (case-insensitive).
     * Pode retornar mais de uma.
     */
    @Query("SELECT b FROM Bula b WHERE LOWER(b.nome) LIKE LOWER(CONCAT('%', :nome, '%'))")
    List<Bula> findByNomeContendo(@Param("nome") String nome);
}

