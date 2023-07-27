package org.dev.model;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.Objects;

@Entity
@jakarta.persistence.Table(name = "login", schema = "user", catalog = "bluewolf")
public class LoginModel {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @jakarta.persistence.Column(name = "id_login", nullable = false)
    private int idLogin;

    public int getIdLogin() {
        return idLogin;
    }

    public void setIdLogin(int idLogin) {
        this.idLogin = idLogin;
    }

    @Basic
    @Column(name = "usuario", nullable = true, length = 255)
    private String usuario;

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    @Basic
    @Column(name = "hashsenha", nullable = true, length = 255)
    private String hashsenha;

    public String getHashsenha() {
        return hashsenha;
    }

    public void setHashsenha(String hashsenha) {
        this.hashsenha = hashsenha;
    }

    @Basic
    @Column(name = "email", nullable = true, length = 255)
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "data_cadastro", nullable = true)
    private Timestamp dataCadastro;

    public Timestamp getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Timestamp dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LoginModel that = (LoginModel) o;
        return idLogin == that.idLogin && Objects.equals(usuario, that.usuario) && Objects.equals(hashsenha, that.hashsenha) && Objects.equals(email, that.email) && Objects.equals(dataCadastro, that.dataCadastro);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idLogin, usuario, hashsenha, email, dataCadastro);
    }
}
