package com.desafiocrea.controller;

import com.desafiocrea.dao.impl.ProfissionalDaoImpl;
import com.desafiocrea.util.Profissional;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/profissional")
@Tag(name = "Profissional", description = "Métodos referentes ao Profissional")
public class ProfissionalController {
    @Autowired
    private ProfissionalDaoImpl profissionalDao;

    @PostMapping("/adicionarProfissional")
    @Operation(summary = "Adicionar profissional", description = "Recebe um profissonal, e o adiciona no banco")
    public ResponseEntity<Profissional> adicionarProfissional(Profissional profissional) {
        return profissionalDao.adicionarProfissional(profissional);
    }

    @GetMapping("/buscarPorCodigo/{codigo}")
    @Operation(summary = "Buscar profissional por codigo", description = "Recebe o codigo de um profissional e o retorna")
    public ResponseEntity<Profissional> buscarPorCodigo(@PathVariable("codigo") String codigo) {
        return profissionalDao.buscarPorCodigo(codigo);
    }

    @GetMapping("/listarProfissionais")
    @Operation(summary = "Listar profissionais", description = "Retorna uma lista com todos os profissionais cadastrados")
    public ResponseEntity<List<Profissional>> listarProfissionais() {
        return profissionalDao.listarProfissionais();
    }

    @DeleteMapping("/excluirProfissional/{codigo}")
    @Operation(summary = "Excluir profissional", description = "Recebe um codigo e exclui o profissional do respectivo codigo")
    public ResponseEntity excluirProfissional(@PathVariable("codigo") String codigo) {
        return profissionalDao.excluirProfissional(codigo);
    }

    @PutMapping("/ativarProfissional/{codigo}")
    @Operation(summary = "Ativar profissional", description = "Recebe o codigo de um profissional e uma descricao de titulo para ser adicionado a ele, possibilitando sua ativação")
    public ResponseEntity<Profissional> ativarProfissional(@PathVariable("codigo") String codigo, String descricao) {
        return profissionalDao.ativarProfissional(codigo, descricao);
    }

    @PutMapping("/desativarProfissional/{codigo}")
    @Operation(summary = "Desativar profissional", description = "Recebe o codigo de um profissional e o desativa, removendo seus titulos")
    public ResponseEntity<Profissional> desativarProfissional(@PathVariable("codigo") String codigo) {
        return profissionalDao.desativarProfissional(codigo);
    }

    @PutMapping("/cancelarProfissional/{codigo}")
    @Operation(summary = "Cancelar profissional", description = "Recebe o codigo de um profissional e o cancela, removendo seus titulos")
    public ResponseEntity<Profissional> cancelarProfissional(@PathVariable("codigo") String codigo) {
        return profissionalDao.cancelarProfissional(codigo);
    }
}
