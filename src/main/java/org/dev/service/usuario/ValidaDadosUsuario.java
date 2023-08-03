package org.dev.service.usuario;

import org.dev.service.MyServicesDAO;
import org.dev.util.ValidaEmail;
import org.dev.util.ValidaSenha;
import org.dev.util.menssagensInternas.GenericMenssage;

public class ValidaDadosUsuario extends MyServicesDAO {

    private final String usuario;
    private final String email;

    private final String senha;

    private final String nome;

    private final String dia;

    private final String mes;

    private final String ano;

    public ValidaDadosUsuario(String usuario, String email, String senha, String nome, String dia, String mes, String ano) {
        this.usuario = usuario;
        this.email = email;
        this.senha = senha;
        this.nome = nome;
        this.dia = dia;
        this.mes = mes;
        this.ano = ano;
    }

    @Override
    public GenericMenssage<Boolean, String> execute() {
        String menssagem;
        Boolean situacao = false;
        if (usuario.equals("") || usuario.length()<5){
            menssagem="Informe um usuario valido!";
        } else if (!ValidaEmail.isValidEmail(email)) {
            menssagem="Informe um email valido!";
        } else if (!ValidaSenha.isStrongPassword(senha)) {
            menssagem="A senha deve ter no mínimo 8 caracteres e incluir:\n" +
                    "- Pelo menos uma letra maiúscula\n" +
                    "- Pelo menos uma letra minúscula\n" +
                    "- Pelo menos um número\n" +
                    "- Pelo menos um caractere especial (!@#$%^&*()_-=[]{};':\"\\|,.<>/?)";
        } else if (nome.equals("")) {
            menssagem="Informe um nome valido";
        } else if (dia.length()<1 || dia.length()>2) {
            menssagem="Data incorreta";
        } else if (mes.length()<1 || mes.length()>2) {
            menssagem="Data incorreta";
        } else if (ano.length()!=4){
            menssagem="Data incorreta";
        } else {
            menssagem="Todos os dados estão validos";
            situacao =true;
        }
        return new GenericMenssage<>(situacao,menssagem);
    }
}
