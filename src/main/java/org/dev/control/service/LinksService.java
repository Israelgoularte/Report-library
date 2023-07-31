package org.dev.control.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.dev.control.UnitControl;
import org.dev.control.repository.LinksRepository;
import org.dev.model.LinksModel;

import java.util.List;

public class LinksService {

    private final LinksRepository linksRepository;
    private static  EntityManager entityManager;
    private static EntityManagerFactory entityManagerFactory;

    private static LinksService instance;

    public static LinksService getInstance() {
        if (instance==null){
            entityManagerFactory = Persistence.createEntityManagerFactory(UnitControl.getInstance().getUnit());
            entityManager = entityManagerFactory.createEntityManager();
            LinksRepository rp = new LinksRepository(entityManager);
            instance = new LinksService(rp);
        }
        return instance;
    }

    public static void closeService(){
        try{
            entityManager.close();
            entityManagerFactory.close();
        }catch (NullPointerException e){
            //Apenas continue
        }
        instance = null;
    }

    public LinksService(LinksRepository linksRepository) {
        this.linksRepository = linksRepository;
    }

    public List<LinksModel> getLista(){
        return linksRepository.getContent();
    }

    public void excluirLink(LinksModel link) throws IllegalAccessException {
        linksRepository.excluirElemento(link);
    }

    public void adicionarLink(String nome,String tipo, String categoria, String descricao, String link) throws IllegalAccessException {
        LinksModel novoLink = new LinksModel();
        novoLink.setNome(nome);
        novoLink.setTipo(tipo);
        novoLink.setcategoria(categoria);
        novoLink.setDescricao(descricao);
        novoLink.setLink(link);
        linksRepository.adicionarElemento(novoLink);
    }

    public void atualizarLink(LinksModel linksModel,String nome,String tipo, String categoria, String link, String descricao){
        String[] oldValues = {linksModel.getNome(),linksModel.getTipo(),linksModel.getcategoria(),linksModel.getLink(),linksModel.getDescricao()};
        linksModel.setNome(nome);
        linksModel.setTipo(tipo);
        linksModel.setcategoria(categoria);
        linksModel.setLink(link);
        linksModel.setDescricao(descricao);
        try{
            this.linksRepository.atualizarElemento(linksModel);
        }catch (Exception e){
            linksModel.setNome(oldValues[0]);
            linksModel.setTipo(oldValues[1]);
            linksModel.setcategoria(oldValues[2]);
            linksModel.setLink(oldValues[3]);
            linksModel.setDescricao(oldValues[4]);
        }
    }

    public List<String> categoriasCadastradas(){
        return this.linksRepository.selectDistinctInfo("categoria");
    }

    public List<String> tiposCadastradas(){
        return this.linksRepository.selectDistinctInfo("tipo");
    }
}
