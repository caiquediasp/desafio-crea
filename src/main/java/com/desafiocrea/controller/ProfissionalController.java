package com.desafiocrea.controller;

import com.desafiocrea.dao.impl.ProfissionalDaoImpl;
import com.desafiocrea.util.Profissional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/profissional")
public class ProfissionalController {
    @Autowired
    private ProfissionalDaoImpl profissionalDao;

    @PostMapping()
    public ResponseEntity<Profissional> adicionarProfissional(Profissional profissional) {
        return profissionalDao.adicionarProfissional(profissional);
    }

    @GetMapping()
    public ResponseEntity<Profissional> buscarPorCodigo(String codigo) {
        return profissionalDao.buscarPorCodigo(codigo);
    }

    @GetMapping("/listarProfissionais")
    public ResponseEntity<List<Profissional>> listarProfissionais() {
        return profissionalDao.listarProfissionais();
    }

    @DeleteMapping()
    public ResponseEntity excluirProfissional(String codigo) {
        return profissionalDao.excluirProfissional(codigo);
    }

    @PutMapping("/ativarProfissional/{codigo}")
    public ResponseEntity<Profissional> ativarProfissional(@PathVariable("codigo") String codigo, String descricao) {
        return profissionalDao.ativarProfissional(codigo, descricao);
    }

    @PutMapping("/desativarProfissional/{codigo}")
    public ResponseEntity<Profissional> desativarProfissional(@PathVariable("codigo") String codigo) {
        return profissionalDao.desativarProfissional(codigo);
    }

    @PutMapping("/cancelarProfissional/{codigo}")
    public ResponseEntity<Profissional> cancelarProfissional(@PathVariable("codigo") String codigo) {
        return profissionalDao.cancelarProfissional(codigo);
    }
}
