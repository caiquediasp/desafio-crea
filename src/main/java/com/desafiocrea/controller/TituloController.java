package com.desafiocrea.controller;

import com.desafiocrea.dao.impl.TituloDaoImpl;
import com.desafiocrea.util.Titulo;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/titulo")
public class TituloController {
    @Autowired
    private TituloDaoImpl tituloDao;

    @PostMapping()
    public ResponseEntity<Titulo> adicionarTitulo(String codigo, String descricao) {
        return tituloDao.adicionarTitulo(codigo, descricao);
    }

    @GetMapping("/buscarTitulo/{codigo}")
    public ResponseEntity<List<Titulo>> buscarTituloPorProfissional(@PathVariable("codigo") String codigo) {
        return tituloDao.buscarTituloPorProfissional(codigo);
    }

    @GetMapping("/listarTitulos")
    public ResponseEntity<List<Titulo>> listarTitulos() {
        return tituloDao.listarTitulos();
    }

    @DeleteMapping("/excluirTitulo")
    public ResponseEntity excluirTitulo(Titulo titulo) {
        return tituloDao.excluirTitulo(titulo);
    }
}