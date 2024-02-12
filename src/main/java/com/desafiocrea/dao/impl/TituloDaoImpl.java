package com.desafiocrea.dao.impl;

import com.desafiocrea.connection.Conexao;
import com.desafiocrea.dao.TituloDao;
import com.desafiocrea.util.Profissional;
import com.desafiocrea.util.SituacaoRegistro;
import com.desafiocrea.util.Titulo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class TituloDaoImpl implements TituloDao {
    @Autowired
    private ProfissionalDaoImpl profissionalDao;
    Connection connection = null;
    PreparedStatement preparedStatement = null;

    @Override
    public ResponseEntity<Titulo> adicionarTitulo(String codigo, String descricao) {
        try {
            connection = Conexao.getDatabaseConnection();
            String sql = "INSERT INTO tb_titulo VALUES (?, ?)";
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, codigo);
            preparedStatement.setString(2, descricao);

            preparedStatement.executeUpdate();

            Profissional profissional = profissionalDao.buscarPorCodigo(codigo).getBody();
            assert profissional != null;
            if(profissional.getSituacaoRegistro() != SituacaoRegistro.ATIVO) {
                sql = "UPDATE tb_profissional SET situacao_registro = 'ATIVO' WHERE codigo = ?";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, codigo);
                preparedStatement.executeUpdate();
            }
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

        return ResponseEntity.ok(new Titulo(codigo, descricao));
    }

    @Override
    public ResponseEntity<List<Titulo>> buscarTituloPorProfissional(String codigo) {
        List<Titulo> listaTitulos = new ArrayList<>();
        ResultSet resultSet = null;

        try {
            connection = Conexao.getDatabaseConnection();
            String sql = "SELECT * FROM tb_titulo WHERE codigo = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, codigo);

            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
               listaTitulos.add(new Titulo(resultSet.getString(1), resultSet.getString(2)));
            }
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
        return ResponseEntity.ok(listaTitulos);
    }

    @Override
    public ResponseEntity<List<Titulo>> listarTitulos() {
        List<Titulo> listaTitulos = new ArrayList<>();
        ResultSet resultSet = null;

        try {
            connection = Conexao.getDatabaseConnection();
            String sql = "SELECT * FROM tb_titulo";
            preparedStatement = connection.prepareStatement(sql);

            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                listaTitulos.add(new Titulo(resultSet.getString(1), resultSet.getString(2)));
            }
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

        return ResponseEntity.ok(listaTitulos);
    }

    @Override
    public ResponseEntity excluirTitulo(String codigo, String descricao) {
        try {
            connection = Conexao.getDatabaseConnection();
            String sql = "DELETE FROM tb_titulo WHERE codigo = ? and descricao = ?";

            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, codigo);
            preparedStatement.setString(2, descricao);

            preparedStatement.executeUpdate();

            Profissional profissional = profissionalDao.buscarPorCodigo(codigo).getBody();
            assert profissional != null;
            if (buscarTituloPorProfissional(codigo).getBody().isEmpty()) {
                sql = "UPDATE tb_profissional SET situacao_registro = 'INATIVO' WHERE codigo = ?";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, codigo);
                preparedStatement.executeUpdate();
            }

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

        return ResponseEntity.ok(HttpStatus.NO_CONTENT);
    }
}
