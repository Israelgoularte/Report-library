package org.dev.control;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.dev.model.PessoaModel;

import java.sql.Date;

public class CadastroPessoaController {
    @FXML
    private Label warning;

    @FXML
    private TextField nomeField;

    @FXML
    private TextField diaNascimento;
    @FXML
    private TextField mesNascimento;
    @FXML
    private TextField anoNascimento;

    @FXML
    private Button cadastrar;

    @FXML
    public void cadastrar() {
        String nome = nomeField.getText();
        String dataDeNascimentoText = anoNascimento.getText() + "-" + mesNascimento.getText() + "-"+diaNascimento.getText();

        // Criar uma instância da entidade Pessoa e preencher os dados
        PessoaModel pessoa = new PessoaModel();
        pessoa.setNome(nome);
        pessoa.setDataNascimento(Date.valueOf(dataDeNascimentoText));
        pessoa.setIdLogin(UsuarioController.getInstance().getLoginModel().getIdLogin());

        // Obter uma instância do EntityManager
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("bluewolf-unit");
        EntityManager em = emf.createEntityManager();

        // Iniciar uma transação
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();

            // Persistir a entidade no banco de dados
            em.persist(pessoa);

            // Confirmar a transação
            tx.commit();
            System.out.println("Cadastro realizado!");

            Stage stage = (Stage) cadastrar.getScene().getWindow();
            stage.close();
        } catch (Exception e) {
            // Lidar com exceções e fazer rollback em caso de erro
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            diaNascimento.setText("");
            mesNascimento.setText("");
            anoNascimento.setText("");
            nomeField.setText("");
            warning.setText(e.getMessage());
        } finally {
            // Fechar o EntityManager
            em.close();
            emf.close();
        }
    }

}
