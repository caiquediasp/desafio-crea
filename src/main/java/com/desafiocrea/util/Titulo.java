package com.desafiocrea.util;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Titulo {
    private String codigo;
    private String descricao;

    public Titulo() {}

    public Titulo(String codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public Titulo(String descricao) {
        this.descricao = descricao;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
