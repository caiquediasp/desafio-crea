package com.desafiocrea.dao.impl;

import com.desafiocrea.connection.Conexao;
import com.desafiocrea.dao.ProfissionalDao;
import com.desafiocrea.util.Profissional;
import com.desafiocrea.util.SituacaoRegistro;
import com.desafiocrea.util.TipoProfissional;
import com.desafiocrea.util.Titulo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class ProfissionalDaoImpl implements ProfissionalDao {
    @Autowired
     private TituloDaoImpl tituloDao;
    Connection connection = null;
    PreparedStatement preparedStatement = null;

    @Override
    public ResponseEntity<Profissional> adicionarProfissional(Profissional profissional) {
        String codigo = UUID.randomUUID().toString().replace("-", "") + "PI";
        profissional.setCodigo(codigo);

       try {
           connection = Conexao.getDatabaseConnection();
           String sql = "INSERT INTO tb_profissional VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
           preparedStatement = connection.prepareStatement(sql);

           profissional.setSituacaoRegistro(SituacaoRegistro.NULA);

           preparedStatement.setString(1, profissional.getCodigo());
           preparedStatement.setString(2, profissional.getNome());
           preparedStatement.setString(3, profissional.getEmail());
           preparedStatement.setString(4, profissional.getSenha());
           preparedStatement.setString(5, profissional.getTelefone());
           preparedStatement.setString(6, profissional.getTipo().toString());
           preparedStatement.setString(7, profissional.getSituacaoRegistro().toString());
           preparedStatement.setDate(8, java.sql.Date.valueOf(profissional.getDataNascimento()));
           preparedStatement.setDate(9, java.sql.Date.valueOf(profissional.getDataRegistro()));
           if (profissional.getTipo() == TipoProfissional.VISADO) {
               preparedStatement.setDate(10, java.sql.Date.valueOf(profissional.getDataVisto()));
           } else {
               preparedStatement.setDate(10, null);
           }

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
        Profissional profissional = new Profissional();
        ResultSet resultSet = null;

        try {
            connection = Conexao.getDatabaseConnection();
            String sql = "SELECT * FROM tb_profissional WHERE codigo = ?";
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, codigo);

           resultSet = preparedStatement.executeQuery();

           while(resultSet.next()) {
               profissional.setCodigo(resultSet.getString(1));
               profissional.setNome(resultSet.getString(2));
               profissional.setEmail(resultSet.getString(3));
               profissional.setSenha(resultSet.getString(4));
               profissional.setTelefone(resultSet.getString(5));
               profissional.setTipo(TipoProfissional.valueOf(resultSet.getString(6)));
               profissional.setSituacaoRegistro(SituacaoRegistro.valueOf(resultSet.getString(7)));
               profissional.setDataNascimento(resultSet.getDate(8).toLocalDate());
               profissional.setDataRegistro(resultSet.getDate(9).toLocalDate());
               profissional.setDataVisto(resultSet.getDate(10).toLocalDate());
            }

        } catch (Exception e) {

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
    public ResponseEntity<List<Profissional>> listarProfissionais() {
        List<Profissional> listaProfissionais = new ArrayList<>();
        ResultSet resultSet = null;

        try {
            connection = Conexao.getDatabaseConnection();
            String sql = "SELECT * FROM tb_profissional";
            preparedStatement = connection.prepareStatement(sql);

            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                String codigo = resultSet.getString(1);
                String nome  = resultSet.getString(2);
                String email = resultSet.getString(3);
                String senha = resultSet.getString(4);
                String telefone = resultSet.getString(5);
                TipoProfissional tipo = TipoProfissional.valueOf(resultSet.getString(6));
                SituacaoRegistro situacaoRegistro = SituacaoRegistro.valueOf(resultSet.getString(7));
                LocalDate dataNascimento = resultSet.getDate(8).toLocalDate();
                LocalDate dataRegistro = resultSet.getDate(9).toLocalDate();
                LocalDate dataVisto = resultSet.getDate(10).toLocalDate();

                listaProfissionais.add(new Profissional(codigo, nome, email, senha, telefone, tipo, situacaoRegistro, dataNascimento, dataRegistro, dataVisto));
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

        return ResponseEntity.ok(listaProfissionais);
    }

    @Override
    public ResponseEntity excluirProfissional(String codigo) {
        try {
            connection = Conexao.getDatabaseConnection();
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

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<Profissional> ativarProfissional(String codigo, Titulo titulo) {
        try {
            connection = Conexao.getDatabaseConnection();
            String sql = "UPDATE FROM tb_profissional SET situacao_registro = ATIVO WHERE codigo = ?";
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, codigo);

            preparedStatement.executeUpdate();

            sql = "INSERT INTO tb_titulo VALUES (?, ?)";
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, codigo);
            preparedStatement.setString(2, titulo.getDescricao());

            preparedStatement.executeUpdate();

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

        return null;
    }

    @Override
    public ResponseEntity<Profissional> desativarProfissional(String codigo) {

        try {
            connection = Conexao.getDatabaseConnection();
            String sql = "UPDATE FROM tb_profissional SET situacao_registro = INATIVO WHERE codigo = ?";
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, codigo);

            preparedStatement.executeUpdate();

            List<Titulo> listaTitulos = tituloDao.buscarTituloPorProfissional(codigo).getBody();
            if(!listaTitulos.isEmpty()) {
                sql = "DELETE FROM tb_titulo WHERE codigo = ?";
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

        return null;
    }

    @Override
    public ResponseEntity<Profissional> cancelarProfissional(String codigo) {

        try {
            connection = Conexao.getDatabaseConnection();
            String sql = "UPDATE FROM tb_profissional SET situacao_registro = CANCELADO WHERE codigo = ?";
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, codigo);

            preparedStatement.executeUpdate();

            List<Titulo> listaTitulos = tituloDao.buscarTituloPorProfissional(codigo).getBody();
            if(!listaTitulos.isEmpty()) {
                sql = "DELETE FROM tb_titulo WHERE codigo = ?";
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

        return null;
    }
}
