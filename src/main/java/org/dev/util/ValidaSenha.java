package org.dev.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidaSenha {
    public static boolean isStrongPassword(String senha) {
        // Verifica se a senha tem pelo menos 8 caracteres
        if (senha.length() < 8) {
            return false;
        }

        // Verifica se a senha contém pelo menos uma letra maiúscula
        if (!senha.matches(".*[A-Z].*")) {
            return false;
        }

        // Verifica se a senha contém pelo menos uma letra minúscula
        if (!senha.matches(".*[a-z].*")) {
            return false;
        }

        // Verifica se a senha contém pelo menos um número
        if (!senha.matches(".*\\d.*")) {
            return false;
        }

        String regex = "[!@#$%^&*()_+\\-=[\\]{};':\"\\\\|,.<>/?]";
        // Verifica se a senha contém pelo menos um caractere especial
        Pattern specialChars = Pattern.compile("[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?]");
        Matcher matcher = specialChars.matcher(senha);
        if (!matcher.find()) {
            return false;
        }

        // Se a senha atende a todas as regras, é considerada forte
        return true;
    }
}
