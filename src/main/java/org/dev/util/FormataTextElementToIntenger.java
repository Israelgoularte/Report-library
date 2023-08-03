package org.dev.util;

import javafx.scene.control.TextFormatter;
import javafx.util.converter.IntegerStringConverter;

public class FormataTextElementToIntenger {

    public static TextFormatter<Integer> createTextFormatter(int length, int max){
        return new TextFormatter<>(new IntegerStringConverter(), null, c -> {
            String newText = c.getControlNewText();
            if (newText.matches("\\d{0,"+length+"}")) {
                // Verifica se o valor está dentro do intervalo permitido (0 a 12)
                int value = newText.isEmpty() ? 0 : Integer.parseInt(newText);
                if (value >= 0 && value <= max) {
                    return c;
                }
            }
            return null;
        });
    }
}
