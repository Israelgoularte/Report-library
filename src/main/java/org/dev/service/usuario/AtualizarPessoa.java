package org.dev.service.usuario;

import org.dev.model.PessoaModel;
import org.dev.model.dao.HibernatePessoaDAO;
import org.dev.service.MyServicesDAO;
import org.dev.util.contexto.EntityManagerContexto;
import org.dev.util.contexto.UsuarioContexto;
import org.dev.util.menssagensInternas.GenericMenssage;

import java.sql.Date;

public class AtualizarPessoa extends MyServicesDAO {
    private final String nome;

    private final String dia;

    private final String mes;

    private final String ano;

    public AtualizarPessoa(String nome, String dia, String mes, String ano) {
        this.nome = nome;
        this.dia = dia;
        this.mes = mes;
        this.ano = ano;
    }

    @Override
    public GenericMenssage<Boolean, String> execute() {
        String dataNascimento = ano + "-" + mes +"-" + dia;
        PessoaModel pessoaModel = UsuarioContexto.getInstance().getContexto().getPessoaByPessoaId();
        pessoaModel.setDataNascimento(Date.valueOf(dataNascimento));
        pessoaModel.setNome(nome);
        try{
            new HibernatePessoaDAO(EntityManagerContexto.getInstance().getContexto()).update(pessoaModel);
            return new GenericMenssage<>(true,"Atualização realizada com sucesso");
        }catch (Exception e){
            return new GenericMenssage<>(false,e.getMessage());
        }
    }
}
