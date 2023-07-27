package org.dev.control.factorys.fxFactory;

import javafx.geometry.Insets;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import org.dev.control.factorys.AbstractFactory;
import org.dev.control.service.StyleService;

public class PasswordFieldFactory implements AbstractFactory<PasswordField, StyleService> {
    @Override
    public PasswordField createBasic(StyleService element) {
        PasswordField pf = new PasswordField();
        pf.setId("passwordfield_basic");
        pf.setBackground(new Background(new BackgroundFill(element.getBackgroudColor_mid(), CornerRadii.EMPTY, Insets.EMPTY)));
        pf.getStylesheets().add("passwordfield_basic");
        return pf;
    }

    @Override
    public PasswordField createNormal(StyleService element) {
        PasswordField pf = new PasswordField();
        pf.setId("passwordfield_normal");
        pf.setBackground(new Background(new BackgroundFill(element.getBackgroudColor_back(), CornerRadii.EMPTY, Insets.EMPTY)));
        pf.getStylesheets().add("passwordfield_normal");
        return pf;
    }

    @Override
    public PasswordField createGreat(StyleService element) {
        PasswordField pf = new PasswordField();
        pf.setId("passwordfield_great");
        pf.setBackground(new Background(new BackgroundFill(element.getBackgroudColor_front(), CornerRadii.EMPTY, Insets.EMPTY)));
        pf.getStylesheets().add("passwordfield_great");
        return pf;
    }
}
