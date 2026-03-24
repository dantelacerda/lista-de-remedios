package com.example.demo;

import com.example.demo.dto.ApiResponse;
import com.example.demo.dto.BulaDTO;
import com.example.demo.exception.GlobalExceptionHandler;
import com.example.demo.service.BulaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Controller do recurso "bulas" (rota base /api/bulas).
 * Salva nova bula (POST) e consulta lista por nome (GET).
 */
@RestController
@RequestMapping("/api/bulas")
public class BulaController {

    @Autowired
    private BulaService bulaService;

    /**
     * POST /api/bulas — salva uma nova bula (nome e validade) no H2.
     * Body: { "nome": "...", "validade": "2026-12-31" }
     */
    @PostMapping
    public ResponseEntity<ApiResponse<BulaDTO>> salvar(@RequestBody BulaDTO body) {
        try {
            var dto = bulaService.salvar(body);
            var response = ApiResponse.success("Bula salva.", dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            return GlobalExceptionHandler.errorResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * GET /api/bulas?nome=xxx — lista bulas cujo nome contém o texto (pode retornar mais de um).
     * Sem query param ou nome vazio: retorna todos.
     */
    @GetMapping
    public ResponseEntity<ApiResponse<List<BulaDTO>>> listarPorNome(@RequestParam(required = false) String nome) {
        try {
            var lista = bulaService.listarPorNome(nome);
            var response = ApiResponse.success("Consulta realizada.", lista);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return GlobalExceptionHandler.errorResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

