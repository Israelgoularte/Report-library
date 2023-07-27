package org.dev.control;

import java.util.List;

public interface Repository<T> {
    public List<T> getList();

    public boolean excluirElemento(T elemento);

    public boolean adicionarElemento(T elemento);

    public boolean atualizarElemento(T elemento);
    
}
