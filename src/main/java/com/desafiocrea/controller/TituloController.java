package com.desafiocrea.controller;

import com.desafiocrea.dao.impl.TituloDaoImpl;
import com.desafiocrea.util.Titulo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/titulo")
@Tag(name = "Titulo", description = "Métodos referentes aos Títulos")
public class TituloController {
    @Autowired
    private TituloDaoImpl tituloDao;

    @PostMapping("/adicionarTitulo")
    @Operation(summary = "Adicionar novo titulo", description = "Recebe o codigo de um profissional e uma descricao, e cria um novo titulo, ligado ao profissional de acordo com o codigo")
    public ResponseEntity<Titulo> adicionarTitulo(String codigo, String descricao) {
        return tituloDao.adicionarTitulo(codigo, descricao);
    }

    @GetMapping("/buscarTitulos/{codigo}")
    @Operation(summary = "Buscar titulo por profissional", description = "Recebe o codigo de um profissional e retorna uma lista com os seus titulos")
    public ResponseEntity<List<Titulo>> buscarTituloPorProfissional(@PathVariable("codigo") String codigo) {
        return tituloDao.buscarTituloPorProfissional(codigo);
    }

    @GetMapping("/listarTitulos")
    @Operation(summary = "Listar titulos", description = "Retorna uma lista com todos os titulos cadastrados")
    public ResponseEntity<List<Titulo>> listarTitulos() {
        return tituloDao.listarTitulos();
    }

    @DeleteMapping("/excluirTitulo")
    @Operation(summary = "Excluir titulo", description = "Recebe o codigo de um profissional e a descricao do titulo, e o exclui")
    public ResponseEntity excluirTitulo(String codigo, String descricao) {
        return tituloDao.excluirTitulo(codigo, descricao);
    }
}
