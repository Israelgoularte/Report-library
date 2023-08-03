package org.dev.control;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import org.dev.model.PessoaModel;
import org.dev.service.fxelement.MenuReportOptionsMyServices;
import org.dev.service.usuario.AtualizarEmailMyServices;
import org.dev.service.usuario.AtualizarPessoa;
import org.dev.service.usuario.LogoutMyServices;
import org.dev.util.contexto.UsuarioContexto;
import org.dev.util.menssagensInternas.GenericMenssage;
import org.dev.view.ViewSimpleFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class DadosCadastraisController implements Initializable {

    @FXML
    private TextField nomeField;

    @FXML
    private TextField diaNascimento;
    @FXML
    private TextField mesNascimento;
    @FXML
    private TextField anoNascimento;

    @FXML
    private TextField email;

    @FXML
    private HBox buttonBox;

    @FXML
    private Menu reportMenu;

    @Override
    public void initialize(URL location, ResourceBundle resources){
        PessoaModel pessoaModel = UsuarioContexto.getInstance().getContexto().getPessoaByPessoaId();
        String[] data = String.valueOf(pessoaModel.getDataNascimento()).split("-");
        this.nomeField.setText(pessoaModel.getNome());
        this.diaNascimento.setText(String.valueOf(data[2]));
        this.mesNascimento.setText(String.valueOf(data[1]));
        this.anoNascimento.setText(String.valueOf(data[0]));
        this.email.setText(UsuarioContexto.getInstance().getContexto().getEmail());
        setEditable(false);

        GenericMenssage<Boolean,String> addMenuReportOptionsResult = new MenuReportOptionsMyServices(reportMenu).execute();
        if (addMenuReportOptionsResult.getMenssageOne()){
            //tratativa de erro;
        }

    }

    public void setEditable(Boolean editable){
        this.nomeField.setEditable(editable);
        this.diaNascimento.setEditable(editable);
        this.mesNascimento.setEditable(editable);
        this.anoNascimento.setEditable(editable);
        this.email.setEditable(editable);
    }
    @FXML
    private void voltar() {
        ViewSimpleFactory.createView("HOME");
    }

    @FXML
    private void sair(){
        GenericMenssage<Boolean,String> logoutResult = new LogoutMyServices().execute();
        if (logoutResult.getMenssageOne()){
            ViewSimpleFactory.createView("LOGIN");
        }else{
            //possibilidade de trataiva de erro;
        }
    }

    @FXML
    private void editarCadastro() {
        setEditable(true);
        Button btnSalvarEdicao = new Button("Salvar");
        buttonBox.getChildren().add(btnSalvarEdicao);
        btnSalvarEdicao.setOnAction(e -> {
            atualizarPessoa();
            atualizarLogin();
            buttonBox.getChildren().remove(btnSalvarEdicao);
            setEditable(false);
        });
    }

    public void atualizarPessoa(){
        String nome = this.nomeField.getText();
        GenericMenssage<Boolean,String> atualizarPessoaResult = new AtualizarPessoa(
                nome,
                this.diaNascimento.getText(),
                this.mesNascimento.getText(),
                this.anoNascimento.getText()
            ).execute();

        if(atualizarPessoaResult.getMenssageOne()){
            //Alerta de sucesso ao atualizar dados
        }else{
            //Alerta de erro;
        }
    }

    public void atualizarLogin(){
        String email = this.email.getText();

        GenericMenssage<Boolean,String> atualizarEmailResult = new AtualizarEmailMyServices(email).execute();
        if (atualizarEmailResult.getMenssageOne()){
            //alerta de sucesso ao atualizar email
        }else{
            //alerta de erro
        }

    }


    public void adicionarLink() {
        ViewSimpleFactory.createView("ADD_REPORT");
    }
}

