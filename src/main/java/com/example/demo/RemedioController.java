package com.example.demo;

import com.example.demo.dto.ApiResponse;
import com.example.demo.dto.RemedioDTO;
import com.example.demo.exception.GlobalExceptionHandler;
import com.example.demo.service.RemedioService;
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
 * Controller do recurso "remedios" (rota base /api/remedios).
 * Salva novo remédio (POST) e consulta lista por nome (GET).
 * Segue .cursorrules: ResponseEntity de ApiResponse, try-catch, uso de Service (não Repository).
 */
@RestController
@RequestMapping("/api/remedios")
public class RemedioController {

    @Autowired
    private RemedioService remedioService;

    /**
     * POST /api/remedios — salva um novo remédio (nome e validade) no H2.
     * Body: { "nome": "...", "validade": "2025-12-31" }
     */
    @PostMapping
    public ResponseEntity<ApiResponse<RemedioDTO>> salvar(@RequestBody RemedioDTO body) {
        try {
            var dto = remedioService.salvar(body);
            var response = ApiResponse.success("Remédio salvo.", dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            return GlobalExceptionHandler.errorResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * GET /api/remedios?nome=xxx — lista remédios cujo nome contém o texto (pode retornar mais de um).
     * Sem query param ou nome vazio: retorna todos.
     */
    @GetMapping
    public ResponseEntity<ApiResponse<List<RemedioDTO>>> listarPorNome(@RequestParam(required = false) String nome) {
        try {
            var lista = remedioService.listarPorNome(nome);
            var response = ApiResponse.success("Consulta realizada.", lista);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return GlobalExceptionHandler.errorResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
