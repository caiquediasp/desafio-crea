package com.desafiocrea.dao;

import com.desafiocrea.util.Titulo;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TituloDao {
    ResponseEntity<Titulo> adicionarTitulo(String codigo, String descricao);

    ResponseEntity<List<Titulo>> buscarTituloPorProfissional(String codigo);

    ResponseEntity<List<Titulo>> listarTitulos();

    ResponseEntity excluirTitulo(Titulo titulo);
}
