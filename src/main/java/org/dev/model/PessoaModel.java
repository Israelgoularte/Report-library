package org.dev.model;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.Objects;

@Entity
@jakarta.persistence.Table(name = "pessoa")
public class PessoaModel {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @jakarta.persistence.Column(name = "id_pessoa", nullable = false)
    private int idPessoa;

    public int getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(int idPessoa) {
        this.idPessoa = idPessoa;
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
    @Column(name = "data_nascimento", nullable = true)
    private Date dataNascimento;

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    @Basic
    @Column(name = "id_login", nullable = true)
    private Integer idLogin;

    public Integer getIdLogin() {
        return idLogin;
    }

    public void setIdLogin(Integer idLogin) {
        this.idLogin = idLogin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PessoaModel that = (PessoaModel) o;
        return idPessoa == that.idPessoa && Objects.equals(nome, that.nome) && Objects.equals(dataNascimento, that.dataNascimento) && Objects.equals(idLogin, that.idLogin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPessoa, nome, dataNascimento, idLogin);
    }

}
