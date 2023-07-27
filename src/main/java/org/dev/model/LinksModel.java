package org.dev.model;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.Objects;

@Entity
@jakarta.persistence.Table(name = "links", schema = "prog_uteis", catalog = "bluewolf")
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
    @Column(name = "tipodeprograma", nullable = true, length = 255)
    private String tipodeprograma;

    public String getTipodeprograma() {
        return tipodeprograma;
    }

    public void setTipodeprograma(String tipodeprograma) {
        this.tipodeprograma = tipodeprograma;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LinksModel that = (LinksModel) o;
        return id == that.id && Objects.equals(nome, that.nome) && Objects.equals(tipodeprograma, that.tipodeprograma) && Objects.equals(link, that.link) && Objects.equals(dataDeCriacao, that.dataDeCriacao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, tipodeprograma, link, dataDeCriacao);
    }
}
