package com.example.demo.service;

import com.example.demo.dto.BulaDTO;

import java.util.List;

/**
 * Contrato do serviço de bulas.
 */
public interface BulaService {

    /**
     * Persiste uma nova bula e retorna o DTO com id gerado.
     */
    BulaDTO salvar(BulaDTO dto);

    /**
     * Busca bulas cujo nome contém o texto informado (pode retornar mais de uma).
     */
    List<BulaDTO> listarPorNome(String nome);
}

