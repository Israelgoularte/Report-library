package org.dev.control;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import org.dev.model.PessoaModel;

import java.net.URL;
import java.sql.Date;
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
        PessoaModel pm = UsuarioController.getInstance().getPessoaModel();
        String[] data = String.valueOf(pm.getDataNascimento()).split("-");
        this.nomeField.setText(pm.getNome());
        this.diaNascimento.setText(String.valueOf(data[2]));
        this.mesNascimento.setText(String.valueOf(data[1]));
        this.anoNascimento.setText(String.valueOf(data[0]));
        setEditable(false);
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
        // Lógica para voltar à tela anterior
        System.out.println("Voltar");

    }

    @FXML
    private void sair(){
        UsuarioController.getInstance().logout();
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
        PessoaModel pm = UsuarioController.getInstance().getPessoaModel();
        pm.setNome(this.nomeField.getText());
        pm.setDataNascimento(
                Date.valueOf(
                this.anoNascimento.getText()+"-"+this.mesNascimento.getText()+"-"+this.diaNascimento.getText()
                )
        );
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("bluewolf-unit");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        try{
            tx.begin();
            em.merge(pm);
            tx.commit();
        }catch (Exception e){
            e.printStackTrace();
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
        }finally {
            emf.close();
            em.close();
        }
    }

    public void atualizarLogin(){

    }

    public void atualizarEndereco(){

    }


}

