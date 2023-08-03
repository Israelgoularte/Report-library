package org.dev.service;

import org.dev.util.menssagensInternas.GenericMenssage;

public interface MyServices<T,D> {
    GenericMenssage<T,D> execute();
}
