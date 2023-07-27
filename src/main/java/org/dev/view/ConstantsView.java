package org.dev.view;

public enum ConstantsView {

    ADICIONAR_LINK(new AdicionarLinksView()),
    SELECT_BD(new SelectBDView()),
    LOGIN(new LoginView()),
    CADASTRO(new CadastroView()),
    CADASTRO_PESSOA(new CadastroPessoaView()),
    DADOS_CADASTRAIS(new DadosCadastraisView()),
    LINKS(new LinksView());


    private ViewInterface vf;

    private ConstantsView(ViewInterface vf){
        this.vf = vf;
    }

    public ViewInterface getVf(){
        return vf;
    }
}
