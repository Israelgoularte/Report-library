package org.dev.service.usuario;

import org.dev.model.PessoaModel;
import org.dev.model.UsuarioModel;
import org.dev.model.dao.HibernatePessoaDAO;
import org.dev.model.dao.HibernateUsuarioDAO;
import org.dev.service.MyServicesDAO;
import org.dev.util.HashUtils;
import org.dev.util.contexto.EntityManagerContexto;
import org.dev.util.menssagensInternas.GenericMenssage;

import java.sql.Date;

public class CadastrarNovoUsuarioMyServices extends MyServicesDAO {
    private final String usuario;

    private final String senha;

    private final String email;

    private final String nome;

    private final String ano;

    private final String mes;

    private final String dia;

    public CadastrarNovoUsuarioMyServices(String usuario, String senha, String email, String nome, String ano, String mes, String dia) {
        this.usuario = usuario;
        this.senha = senha;
        this.email = email;
        this.nome = nome;
        this.ano = ano;
        this.mes = mes;
        this.dia = dia;
    }

    @Override
    public GenericMenssage<Boolean, String> execute() {
        String dataNascimento = ano + "-" + mes + "-" + dia;
        String hashSenha = HashUtils.hashSenha(senha);
        UsuarioModel novoUsuario = new UsuarioModel();
        novoUsuario.setUsuario(usuario);
        novoUsuario.setEmail(email);
        novoUsuario.setHashsenha(hashSenha);

        PessoaModel novaPessoa = new PessoaModel();
        novaPessoa.setNome(nome);
        novaPessoa.setDataNascimento(Date.valueOf(dataNascimento));

        novoUsuario.setPessoaByPessoaId(novaPessoa);

        try{
            new HibernatePessoaDAO(EntityManagerContexto.getInstance().getContexto())
                    .save(novaPessoa);
            new HibernateUsuarioDAO(EntityManagerContexto.getInstance().getContexto())
                    .save(novoUsuario);
            return new GenericMenssage<>(true,"Cadastro Realizado com Sucesso");
        }catch (Exception e){
            return new GenericMenssage<>(false,e.getMessage());
        }

    }
}
