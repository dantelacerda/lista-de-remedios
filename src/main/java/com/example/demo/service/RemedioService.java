package com.example.demo.service;

import com.example.demo.dto.RemedioDTO;

import java.util.List;

/**
 * Contrato do serviço de remédios (regras: lógica em ServiceImpl, retorno em DTOs).
 */
public interface RemedioService {

    /**
     * Persiste um novo remédio e retorna o DTO com id gerado.
     */
    RemedioDTO salvar(RemedioDTO dto);

    /**
     * Busca remédios cujo nome contém o texto informado (pode retornar mais de um).
     */
    List<RemedioDTO> listarPorNome(String nome);
}
