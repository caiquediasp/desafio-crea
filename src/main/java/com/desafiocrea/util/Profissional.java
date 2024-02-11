package com.desafiocrea.util;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.util.Objects;

public class Profissional {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String codigo;
    private String nome;
    private String email;
    private String senha;
    private String telefone;
    private TipoProfissional tipo;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private SituacaoRegistro situacaoRegistro;
    private LocalDate dataNascimento;
    private LocalDate dataRegistro;
    private LocalDate dataVisto;

    public Profissional() {}

    public Profissional(String codigo, String nome, String email
            , String senha, String telefone, TipoProfissional tipo, SituacaoRegistro situacaoRegistro
            , LocalDate dataNascimento, LocalDate dataRegistro, LocalDate dataVisto) {
        this.codigo = codigo;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.telefone = telefone;
        this.tipo = tipo;
        this.situacaoRegistro = situacaoRegistro;
        this.dataNascimento = dataNascimento;
        this.dataRegistro = dataRegistro;
        this.dataVisto = dataVisto;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }


    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public TipoProfissional getTipo() {
        return tipo;
    }

    public void setTipo(TipoProfissional tipo) {
        this.tipo = tipo;
    }

    public SituacaoRegistro getSituacaoRegistro() {
        return situacaoRegistro;
    }

    public void setSituacaoRegistro(SituacaoRegistro situacaoRegistro) {
        this.situacaoRegistro = situacaoRegistro;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public LocalDate getDataRegistro() {
        return dataRegistro;
    }

    public void setDataRegistro(LocalDate dataRegistro) {
        this.dataRegistro = dataRegistro;
    }

    public LocalDate getDataVisto() {
        return dataVisto;
    }

    public void setDataVisto(LocalDate dataVisto) {
        this.dataVisto = dataVisto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Profissional that = (Profissional) o;
        return Objects.equals(codigo, that.codigo) && Objects.equals(nome, that.nome)
                && Objects.equals(email, that.email) && Objects.equals(senha, that.senha)
                && Objects.equals(dataNascimento, that.dataNascimento) && Objects.equals(dataRegistro, that.dataRegistro)
                && Objects.equals(telefone, that.telefone) && Objects.equals(situacaoRegistro, that.situacaoRegistro)
                && Objects.equals(dataVisto, that.dataVisto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo, nome, email, senha, dataNascimento, dataRegistro, telefone, situacaoRegistro, dataVisto);
    }
}
