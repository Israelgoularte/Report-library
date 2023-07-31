package org.dev.view;

public enum ConstantsView {

    ADICIONAR_LINK(new addReportView()),

    EDITAR_REPORT(new EditarReportView()),
    LOGIN(new LoginView()),
    CADASTRO(new CadastroView()),
    DADOS_CADASTRAIS(new DadosCadastraisView()),
    HOME(new HomeView());


    private ViewInterface vf;

    private ConstantsView(ViewInterface vf){
        this.vf = vf;
    }

    public ViewInterface getVf(){
        return vf;
    }
}
