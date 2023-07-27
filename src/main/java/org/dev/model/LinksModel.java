package org.dev.model;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.Objects;

@Entity
@jakarta.persistence.Table(name = "links")
public class LinksModel {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @jakarta.persistence.Column(name = "id", nullable = false)
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "nome", nullable = true, length = 255)
    private String nome;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


    @Basic
    @Column(name = "tipo", nullable = true, length = 255)
    private String tipo;

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Basic
    @Column(name = "categoria", nullable = true, length = 255)
    private String categoria;

    public String getcategoria() {
        return categoria;
    }

    public void setcategoria(String tipodeprograma) {
        this.categoria = tipodeprograma;
    }

    @Basic
    @Column(name = "link", nullable = true, length = 255)
    private String link;

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Basic
    @Column(name = "data_de_criacao", nullable = true)
    private Date dataDeCriacao;

    public Date getDataDeCriacao() {
        return dataDeCriacao;
    }

    public void setDataDeCriacao(Date dataDeCriacao) {
        this.dataDeCriacao = dataDeCriacao;
    }

    @Basic
    @Column(name = "decricao", nullable = true)
    private String descricao;

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LinksModel that = (LinksModel) o;
        return id == that.id && Objects.equals(nome, that.nome) && Objects.equals(categoria, that.categoria) && Objects.equals(descricao, that.descricao) && Objects.equals(link, that.link) && Objects.equals(dataDeCriacao, that.dataDeCriacao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, categoria, descricao, link, dataDeCriacao);
    }
}
