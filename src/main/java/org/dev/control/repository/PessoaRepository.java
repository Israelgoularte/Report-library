package org.dev.control.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.dev.model.PessoaModel;

import java.sql.Date;

public class PessoaRepository extends Repository<PessoaModel,String> {
    private PessoaModel pessoa;

    public PessoaRepository(EntityManager entityManager){
        super(entityManager);
    }
    @Override
    public PessoaModel getContent() throws IllegalAccessException {
        if(pessoa == null){
            throw new IllegalAccessException("Acesso Negado aos Dados Pessoais");
        }
        return pessoa;
    }

    @Override
    public void excluirElemento(String ...info) throws IllegalAccessException {
        //Independente do parametro Info, ira excluir o PessoaModel (pessoa) do banco de dados que esta logado.
        //caso não estiver logado ira retornar uma Exception
        if(pessoa!=null) {
            EntityTransaction ts = entityManager.getTransaction();
            try {
                ts.begin();
                PessoaModel mergedElement = entityManager.merge(pessoa);
                entityManager.remove(mergedElement);
                ts.commit();
                pessoa = null;
            } catch (Exception e) {
                ts.rollback();
                e.printStackTrace();
            }
        }else throw new IllegalAccessException("Acesso Negado");
    }
    @Override
    public void adicionarElemento(String ...info) throws IllegalAccessException,IllegalArgumentException {
        if(this.pessoa == null && info.length==3){
            PessoaModel pessoaModel = new PessoaModel();
            pessoaModel.setIdLogin(Integer.valueOf(info[0]));
            pessoaModel.setNome(info[1]);
            pessoaModel.setDataNascimento(Date.valueOf(info[2]));

            EntityTransaction ts = entityManager.getTransaction();
            try {
                ts.begin();
                entityManager.persist(pessoaModel);
                ts.commit();
                pessoa = pessoaModel;
            } catch (Exception e) {
                ts.rollback();
                e.printStackTrace();
            }
        }else throw new IllegalAccessException("Acesso Negado");
    }

    @Override
    public void atualizarElemento(String ...info) throws IllegalAccessException,IllegalArgumentException {
        if(pessoa == null && info !=null && info.length==1){
            pessoa = entityManager.createQuery("SELECT l FROM PessoaModel as l WHERE l.idLogin = :id_login", PessoaModel.class)
                    .setParameter("id_login", info[0])
                    .getSingleResult();
        }else if(pessoa !=null && info!=null && info.length==3){
            //atualizar cadastro
            pessoa.setNome(info[0]);
            pessoa.setDataNascimento(Date.valueOf(info[1]));
            pessoa.setIdLogin(Integer.valueOf(info[2]));
            EntityTransaction tx = entityManager.getTransaction();
            try{
                tx.begin();
                entityManager.merge(pessoa);
                tx.commit();
            }catch (Exception e){
                e.printStackTrace();
                if (tx != null && tx.isActive()) {
                    tx.rollback();
                }
            }
        }else if(pessoa !=null && info == null){
            this.pessoa = null;
        }else throw new IllegalAccessException("Acesso Negado");
    }
}
