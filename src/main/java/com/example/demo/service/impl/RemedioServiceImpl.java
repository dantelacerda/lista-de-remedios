package com.example.demo.service.impl;

import com.example.demo.dto.RemedioDTO;
import com.example.demo.entity.Remedio;
import com.example.demo.repository.RemedioRepository;
import com.example.demo.service.RemedioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Implementação do serviço de remédios: usa apenas o repositório (sem acesso direto ao DB).
 * Retorna DTOs; entidades só para leitura/gravação via repositório (regras .cursorrules).
 */
@Service
public class RemedioServiceImpl implements RemedioService {

    @Autowired
    private RemedioRepository remedioRepository;

    @Override
    @Transactional
    public RemedioDTO salvar(RemedioDTO dto) {
        var entity = new Remedio(dto.nome(), dto.validade());
        var saved = remedioRepository.save(entity);
        return RemedioDTO.of(saved.getId(), saved.getNome(), saved.getValidade());
    }

    @Override
    public List<RemedioDTO> listarPorNome(String nome) {
        // Nome vazio ou em branco: retorna todos (comportamento comum em filtros).
        var lista = nome == null || nome.isBlank()
                ? remedioRepository.findAll()
                : remedioRepository.findByNomeContendo(nome.trim());
        return lista.stream()
                .map(r -> RemedioDTO.of(r.getId(), r.getNome(), r.getValidade()))
                .toList();
    }
}
