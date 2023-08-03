package org.dev.model;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "pessoa", schema = "software_warehouse", catalog = "uznvzfyz")
public class PessoaModel {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_pessoa", nullable = false)
    private int idPessoa;
    @Basic
    @Column(name = "nome", nullable = true, length = 255)
    private String nome;
    @Basic
    @Column(name = "data_nascimento", nullable = true)
    private Date dataNascimento;

    public int getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(int idPessoa) {
        this.idPessoa = idPessoa;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PessoaModel that = (PessoaModel) o;
        return idPessoa == that.idPessoa && Objects.equals(nome, that.nome) && Objects.equals(dataNascimento, that.dataNascimento);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPessoa, nome, dataNascimento);
    }

}
