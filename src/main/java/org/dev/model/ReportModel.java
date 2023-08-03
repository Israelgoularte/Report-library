package org.dev.model;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "report", schema = "software_warehouse", catalog = "uznvzfyz")
public class ReportModel {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "nome", nullable = true, length = 255)
    private String nome;
    @Basic
    @Column(name = "categoria", nullable = true, length = 255)
    private String categoria;
    @Basic
    @Column(name = "link", nullable = true, length = 255)
    private String link;
    @Basic
    @Column(name = "data_de_criacao", nullable = true)
    private Date dataDeCriacao;
    @Basic
    @Column(name = "descricao", nullable = true, length = -1)
    private String descricao;
    @Basic
    @Column(name = "tipo", nullable = true, length = 255)
    private String tipo;
    @Basic
    @Column(name = "usuario_id", nullable = false)
    private int usuarioId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Date getDataDeCriacao() {
        return dataDeCriacao;
    }

    public void setDataDeCriacao(Date dataDeCriacao) {
        this.dataDeCriacao = dataDeCriacao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReportModel that = (ReportModel) o;
        return id == that.id && usuarioId == that.usuarioId && Objects.equals(nome, that.nome) && Objects.equals(categoria, that.categoria) && Objects.equals(link, that.link) && Objects.equals(dataDeCriacao, that.dataDeCriacao) && Objects.equals(descricao, that.descricao) && Objects.equals(tipo, that.tipo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, categoria, link, dataDeCriacao, descricao, tipo, usuarioId);
    }

}
