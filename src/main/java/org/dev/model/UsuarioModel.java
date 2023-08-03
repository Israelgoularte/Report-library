package org.dev.model;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "usuario", schema = "software_warehouse", catalog = "uznvzfyz")
public class UsuarioModel {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_usuario", nullable = false)
    private int idUsuario;
    @Basic
    @Column(name = "usuario", nullable = false, length = 255)
    private String usuario;
    @Basic
    @Column(name = "hashsenha", nullable = true, length = 255)
    private String hashsenha;
    @Basic
    @Column(name = "email", nullable = false, length = 255)
    private String email;
    @Basic
    @Column(name = "data_cadastro", nullable = true)
    private Date dataCadastro;

    @OneToMany(mappedBy = "usuarioId")
    private Collection<ReportModel> reportsByIdUsuario;
    @ManyToOne
    @JoinColumn(name = "pessoa_id", referencedColumnName = "id_pessoa")
    private PessoaModel pessoaByPessoaId;

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getHashsenha() {
        return hashsenha;
    }

    public void setHashsenha(String hashsenha) {
        this.hashsenha = hashsenha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsuarioModel that = (UsuarioModel) o;
        return idUsuario == that.idUsuario && Objects.equals(usuario, that.usuario) && Objects.equals(hashsenha, that.hashsenha) && Objects.equals(email, that.email) && Objects.equals(dataCadastro, that.dataCadastro);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUsuario, usuario, hashsenha, email, dataCadastro);
    }

    public Collection<ReportModel> getReportsByIdUsuario() {
        return reportsByIdUsuario;
    }

    public void setReportsByIdUsuario(Collection<ReportModel> reportsByIdUsuario) {
        this.reportsByIdUsuario = reportsByIdUsuario;
    }

    public PessoaModel getPessoaByPessoaId() {
        return pessoaByPessoaId;
    }

    public void setPessoaByPessoaId(PessoaModel pessoaByPessoaId) {
        this.pessoaByPessoaId = pessoaByPessoaId;
    }
}
