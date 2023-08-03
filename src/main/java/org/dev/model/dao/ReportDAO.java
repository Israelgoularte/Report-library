package org.dev.model.dao;

import org.dev.model.ReportModel;
import org.dev.model.UsuarioModel;

import java.util.List;

public interface ReportDAO extends GenericDAO<ReportModel, Integer> {
    List<ReportModel> findByUser(UsuarioModel usuarioModel);
    List<String> distinctTipo(UsuarioModel usuarioModel);
    List<String> distinctCategoria(UsuarioModel usuarioModel);
}