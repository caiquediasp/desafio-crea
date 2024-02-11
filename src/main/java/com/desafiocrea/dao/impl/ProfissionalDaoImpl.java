package com.desafiocrea.dao.impl;

import com.desafiocrea.dao.ProfissionalDao;
import com.desafiocrea.util.Profissional;
import com.desafiocrea.util.TipoProfissional;
import com.desafiocrea.util.Titulo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class ProfissionalDaoImpl implements ProfissionalDao {
    Connection connection = null;
    PreparedStatement preparedStatement = null;

    @Override
    public ResponseEntity<Profissional> adicionarProfissional(Profissional profissional) {
        String codigo = UUID.randomUUID().toString().replace("-", "") + "PI";
        profissional.setCodigo(codigo);

        if (profissional.getTipo() == TipoProfissional.REGISTRADO)
            profissional.setDataVisto(null);

        profissional.setSituacaoRegistro(null);

       try {
           String sql = "INSERT INTO tb_profissional VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
           preparedStatement = connection.prepareStatement(sql);

           preparedStatement.setString(1, profissional.getCodigo());
           preparedStatement.setString(2, profissional.getNome());
           preparedStatement.setString(3, profissional.getEmail());
           preparedStatement.setString(4, profissional.getSenha());
           preparedStatement.setString(5, profissional.getTelefone());
           preparedStatement.setString(6, profissional.getTipo().toString());
           preparedStatement.setString(7, profissional.getSituacaoRegistro().toString());
           preparedStatement.setDate(8, java.sql.Date.valueOf(profissional.getDataNascimento()));
           preparedStatement.setDate(9, java.sql.Date.valueOf(profissional.getDataRegistro()));
           preparedStatement.setDate(10, java.sql.Date.valueOf(profissional.getDataVisto()));

           preparedStatement.executeUpdate();
       }
       catch(Exception e) {
           e.printStackTrace();
       } finally {
           try {
               if (preparedStatement != null) {
                   preparedStatement.close();
               }
               if (connection != null) {
                   preparedStatement.close();
               }
           } catch (SQLException e) {
               e.printStackTrace();
           }
       }

        return ResponseEntity.ok(profissional);
    }

    @Override
    public ResponseEntity<Profissional> buscarPorCodigo(String codigo) {
        return null;
    }

    @Override
    public ResponseEntity<List<Profissional>> listarProfissionais() {
        return null;
    }

    @Override
    public ResponseEntity excluirProfissional(String codigo) {
        try {
            String sql = "DELETE FROM tb_profissional WHERE codigo = ?";

            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, codigo);

            preparedStatement.executeUpdate();

            // METODO PARA DELETAR TITULO AQUI!!!!!!!!!!!!!

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return new ResponseEntity(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Profissional> ativarProfissional(String codigo, Titulo titulo) {
        return null;
    }

    @Override
    public ResponseEntity<Profissional> desativarProfissional(String codigo) {
        return null;
    }

    @Override
    public ResponseEntity<Profissional> cancelarProfissional(String codigo) {
        return null;
    }
}
