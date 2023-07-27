package org.dev.control.views;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.dev.control.service.UsuarioService;
import org.dev.view.ViewSimpleFactory;

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

        try {
            UsuarioService USInstance = UsuarioService.getInstance();
            USInstance.getPessoa().adicionarElemento(""+USInstance.getUser().getContent().getIdLogin()
                    ,nome
                    ,dataDeNascimentoText);
            ViewSimpleFactory.createView("LINKS");
        } catch (IllegalAccessException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            ViewSimpleFactory.createView("LOGIN");
        }
    }

}
