package org.dev.model.dao;

import org.dev.model.PessoaModel;
import org.dev.model.UsuarioModel;

public interface UsuarioDAO extends GenericDAO<UsuarioModel, Integer> {
    UsuarioModel findByUsernameAndPassword(String username, String password);
    PessoaModel getPessoaByLogin(UsuarioModel usuarioModel);
}