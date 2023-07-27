package org.dev.control.views;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import org.dev.control.service.UsuarioService;
import org.dev.model.PessoaModel;
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
    private TextField cep;

    @FXML
    private TextField rua;

    @FXML
    private TextField numero;

    @FXML
    private TextField complemento;

    @FXML
    private TextField bairro;

    @FXML
    private TextField cidade;

    @FXML
    private TextField estado;

    @FXML
    private TextField pais;

    @FXML
    private TextField email;

    @FXML
    private Button voltarButton;

    @FXML
    private Button editarCadastroButton;

    @FXML
    private HBox buttonBox;

    @Override
    public void initialize(URL location, ResourceBundle resources){
        PessoaModel pm = null;
        try {
            pm = UsuarioService.getInstance().getPessoa().getContent();
            String[] data = String.valueOf(pm.getDataNascimento()).split("-");
            this.nomeField.setText(pm.getNome());
            this.diaNascimento.setText(String.valueOf(data[2]));
            this.mesNascimento.setText(String.valueOf(data[1]));
            this.anoNascimento.setText(String.valueOf(data[0]));
            this.email.setText(UsuarioService.getInstance().getUser().getContent().getEmail());
            setEditable(false);
        } catch (IllegalAccessException e) {
            System.out.println(e.getMessage());
            //ADICIONAR TELA DE ERRO
        } catch (NullPointerException e){
            System.out.println(e.getMessage());
            //ADICIONAR TELA DE ERRO
        }
    }

    public void setEditable(Boolean editable){
        this.nomeField.setEditable(editable);
        this.diaNascimento.setEditable(editable);
        this.mesNascimento.setEditable(editable);
        this.anoNascimento.setEditable(editable);
        this.cep.setEditable(editable);
        this.rua.setEditable(editable);
        this.numero.setEditable(editable);
        this.bairro.setEditable(editable);
        this.cidade.setEditable(editable);
        this.estado.setEditable(editable);
        this.complemento.setEditable(editable);
        this.pais.setEditable(editable);
        this.email.setEditable(editable);
    }
    @FXML
    private void voltar() {
        ViewSimpleFactory.createView("LINKS");
    }

    @FXML
    private void sair(){
        try {
            UsuarioService.getInstance().logout();
        } catch (IllegalAccessException e) {
            System.out.println(e.getMessage());
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
            atualizarEndereco();
            buttonBox.getChildren().remove(btnSalvarEdicao);
            setEditable(false);
        });
    }

    public void atualizarPessoa(){
        String nome = this.nomeField.getText();
        String dataNascimento = this.anoNascimento.getText()+"-"+this.mesNascimento.getText()+"-"+this.diaNascimento.getText();
        try {
            UsuarioService.getInstance().getUser().atualizarElemento(nome,dataNascimento);
        } catch (IllegalAccessException e) {
            System.out.println(e.getMessage());
        }

    }

    public void atualizarLogin(){

    }

    public void atualizarEndereco(){

    }


    public void adicionarLink() {
        ViewSimpleFactory.createView("ADICIONAR_LINK");
    }
}

