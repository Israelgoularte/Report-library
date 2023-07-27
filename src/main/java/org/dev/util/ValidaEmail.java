package org.dev.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidaEmail {
    public static boolean isValidEmail(String email) {
        // Expressão regular para verificar o formato do email
        String emailRegex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";

        // Compila a expressão regular em um padrão
        Pattern pattern = Pattern.compile(emailRegex);

        // Cria um objeto Matcher para fazer a correspondência do email com o padrão
        Matcher matcher = pattern.matcher(email);

        // Verifica se o email corresponde ao padrão
        return matcher.matches();
    }
}
