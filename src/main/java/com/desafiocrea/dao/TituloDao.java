package com.desafiocrea.dao;

import com.desafiocrea.util.Titulo;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TituloDao {
    ResponseEntity<Titulo> adicionarTitulo(String codigo, Titulo titulo);

    ResponseEntity<List<Titulo>> listarTitulos(String codigo);

    ResponseEntity excluirTitulo(String codigo);
}
