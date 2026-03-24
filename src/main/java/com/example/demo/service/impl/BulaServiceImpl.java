package com.example.demo.service.impl;

import com.example.demo.dto.BulaDTO;
import com.example.demo.entity.Bula;
import com.example.demo.repository.BulaRepository;
import com.example.demo.service.BulaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Implementação do serviço de bulas.
 * Usa apenas o repositório (sem acesso direto ao DB).
 */
@Service
public class BulaServiceImpl implements BulaService {

    @Autowired
    private BulaRepository bulaRepository;

    @Override
    @Transactional
    public BulaDTO salvar(BulaDTO dto) {
        var entity = new Bula(dto.nome(), dto.validade());
        var saved = bulaRepository.save(entity);
        return BulaDTO.of(saved.getId(), saved.getNome(), saved.getValidade());
    }

    @Override
    public List<BulaDTO> listarPorNome(String nome) {
        // Nome vazio ou em branco: retorna todos.
        var lista = nome == null || nome.isBlank()
                ? bulaRepository.findAll()
                : bulaRepository.findByNomeContendo(nome.trim());

        return lista.stream()
                .map(b -> BulaDTO.of(b.getId(), b.getNome(), b.getValidade()))
                .toList();
    }
}

