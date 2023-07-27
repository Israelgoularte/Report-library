package org.dev.control;

import jakarta.persistence.NoResultException;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import org.dev.model.LoginModel;
import org.dev.util.HashUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.dev.view.CadastroView;
import org.dev.view.DadosCadastraisView;
import org.dev.view.ViewSimpleFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label warning;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        passwordField.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                try {
                    login();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    @FXML
    public void login() throws IOException {
        // Lógica para verificar o usuário e senha informados

        if(authenticateUser()){
            ViewSimpleFactory.createView("HOME");
        }else{
            warning.setText("Falha na autentificação");
            usernameField.setText("");
            passwordField.setText("");
        }
    }

    @FXML
    public void signup() throws IOException {
        ViewSimpleFactory.createView("CADASTRO");
    }

    // Método para realizar a autenticação do usuário
    public boolean authenticateUser() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        password = HashUtils.hashSenha(password);

        // Obtém uma instância do EntityManager
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("bluewolf-unit");
        EntityManager em = emf.createEntityManager();

        try {
            // Realiza a consulta ao banco de dados para verificar o usuário
            LoginModel login = em.createQuery("SELECT l FROM LoginModel as l WHERE l.usuario = :username and l.hashsenha = :password", LoginModel.class)
                    .setParameter("username", username)
                    .setParameter("password", password)
                    .getSingleResult();
            System.out.println(login.getEmail());
            System.out.println(login.getUsuario());
            System.out.println(login.getIdLogin());

            // Defina os valores do loginModel com base nos dados retornados do banco de dados
            UsuarioController.getInstance().setLoginModel(login);
            return true;
        } catch (NoResultException e) {
            // Usuário não encontrado
            System.out.println("Usuário ou senha incorreto!");
            return false;
        } finally {
            // Feche o EntityManager
            em.close();
            emf.close();
        }
    }

    @FXML
    public void truncade(){
        // Obtém uma instância do EntityManager
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("bluewolf-unit");
        EntityManager em = emf.createEntityManager();

        try {
            // Inicia uma transação
            em.getTransaction().begin();

            // Executa a operação de truncagem na tabela principal (user.login)
            // O cascade irá propagar a operação de truncagem para as tabelas relacionadas com cascata
            em.createNativeQuery("TRUNCATE TABLE \"user\".login CASCADE").executeUpdate();

            // Comita a transação
            em.getTransaction().commit();

            System.out.println("Truncagem realizada com sucesso no banco de dados 'user' com cascade.");
        } catch (Exception e) {
            // Se ocorrer algum erro, faça rollback na transação
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.out.println("Erro ao realizar a truncagem no banco de dados 'user' com cascade: " + e.getMessage());
        } finally {
            // Feche o EntityManager
            em.close();
            emf.close();
        }
    }

}
