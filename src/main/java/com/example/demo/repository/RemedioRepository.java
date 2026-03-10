package com.example.demo.repository;

import com.example.demo.entity.Remedio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repositório JPA para Remédio (apenas interface; Spring Data implementa).
 * Consulta por nome via JPQL (regras .cursorrules).
 */
@Repository
public interface RemedioRepository extends JpaRepository<Remedio, Long> {

    /**
     * Lista remédios cujo nome contém o texto informado (case-insensitive).
     * Pode retornar mais de um.
     */
    @Query("SELECT r FROM Remedio r WHERE LOWER(r.nome) LIKE LOWER(CONCAT('%', :nome, '%'))")
    List<Remedio> findByNomeContendo(@Param("nome") String nome);
}
