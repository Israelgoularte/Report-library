package org.dev.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashUtils {

    public static String hashSenha(String senha) {
        try {
            // Cria uma instância do algoritmo de hash SHA-256
            MessageDigest digest = MessageDigest.getInstance("SHA-256");

            // Converte a senha em bytes usando a codificação UTF-8
            byte[] senhaBytes = senha.getBytes(StandardCharsets.UTF_8);

            // Calcula a hash da senha
            byte[] hashBytes = digest.digest(senhaBytes);

            // Converte a hash em uma string hexadecimal
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            // Tratar a exceção adequada
            e.printStackTrace();
            return null;
        }
    }


}

