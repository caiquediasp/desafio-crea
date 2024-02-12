package com.desafiocrea.dao;

import com.desafiocrea.util.Profissional;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProfissionalDao {
    ResponseEntity<Profissional> adicionarProfissional(Profissional profissional);

    ResponseEntity<Profissional> buscarPorCodigo(String codigo);

    ResponseEntity<List<Profissional>> listarProfissionais();

    ResponseEntity excluirProfissional(String codigo);

    ResponseEntity<Profissional> ativarProfissional(String codigo, String descricao);

    ResponseEntity<Profissional> desativarProfissional(String codigo);

    ResponseEntity<Profissional> cancelarProfissional(String codigo);
}
