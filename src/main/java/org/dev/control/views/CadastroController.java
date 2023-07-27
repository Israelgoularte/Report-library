package org.dev.control;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.dev.model.LoginModel;
import org.dev.util.HashUtils;
import org.dev.view.ViewSimpleFactory;

public class CadastroController {

    @FXML
    private TextField nomeField;

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField senhaField;

    @FXML
    private Label warning;

    @FXML
    public void cadastrar() {
        String nome = nomeField.getText();
        String email = emailField.getText();
        String senha = senhaField.getText();
        senha = HashUtils.hashSenha(senha);

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("bluewolf-unit");
        EntityManager em = emf.createEntityManager();

        try{
            LoginModel usuario = new LoginModel();
            usuario.setUsuario(nome);
            usuario.setEmail(email);
            usuario.setHashsenha(senha);

            em.getTransaction().begin();
            em.persist(usuario);
            em.getTransaction().commit();
            ViewSimpleFactory.createView("LOGIN");
        }catch (RuntimeException e) {
            warning.setText(e.getCause().getMessage().split("Detalhe: Key ")[1]);
        }finally {
            em.close();
            emf.close();
        }
    }


    @FXML
    public void voltar(){
        ViewSimpleFactory.createView("LOGIN");
    }
}

