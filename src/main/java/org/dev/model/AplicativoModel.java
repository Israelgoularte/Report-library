package org.dev.model;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "aplicativo", schema = "user", catalog = "bluewolf")
public class AplicativoModel {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idaplicativo", nullable = false)
    private int idaplicativo;
    @Basic
    @Column(name = "nome", nullable = false, length = 255)
    private String nome;
    @Basic
    @Column(name = "data_cadastro", nullable = false)
    private Date dataCadastro;
    @Basic
    @Column(name = "link_icon", nullable = false, length = 255)
    private String linkIcon;
    @Basic
    @Column(name = "link_background", nullable = false, length = 255)
    private String linkBackground;
    @Basic
    @Column(name = "descricao", nullable = true, length = -1)
    private String descricao;
    @Basic
    @Column(name = "link_inicializador", nullable = false, length = 255)
    private String linkInicializador;

    public int getIdaplicativo() {
        return idaplicativo;
    }

    public void setIdaplicativo(int idaplicativo) {
        this.idaplicativo = idaplicativo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public String getLinkIcon() {
        return linkIcon;
    }

    public void setLinkIcon(String linkIcon) {
        this.linkIcon = linkIcon;
    }

    public String getLinkBackground() {
        return linkBackground;
    }

    public void setLinkBackground(String linkBackground) {
        this.linkBackground = linkBackground;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getLinkInicializador() {
        return linkInicializador;
    }

    public void setLinkInicializador(String linkInicializador) {
        this.linkInicializador = linkInicializador;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AplicativoModel that = (AplicativoModel) o;
        return idaplicativo == that.idaplicativo && Objects.equals(nome, that.nome) && Objects.equals(dataCadastro, that.dataCadastro) && Objects.equals(linkIcon, that.linkIcon) && Objects.equals(linkBackground, that.linkBackground) && Objects.equals(descricao, that.descricao) && Objects.equals(linkInicializador, that.linkInicializador);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idaplicativo, nome, dataCadastro, linkIcon, linkBackground, descricao, linkInicializador);
    }
}
