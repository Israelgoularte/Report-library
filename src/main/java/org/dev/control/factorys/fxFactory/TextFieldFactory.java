package org.dev.control.factorys.fxFactory;

import javafx.geometry.Insets;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import org.dev.control.factorys.AbstractFactory;
import org.dev.control.service.StyleService;

public class TextFieldFactory implements AbstractFactory<TextField, StyleService> {
    @Override
    public TextField createBasic(StyleService element) {
        TextField tf = new TextField();
        tf.setId("textfield_basic");
        tf.setBackground(new Background(new BackgroundFill(element.getBackgroudColor_mid(), CornerRadii.EMPTY, Insets.EMPTY)));
        tf.getStylesheets().add("textfield_basic");
        return tf;
    }

    @Override
    public TextField createNormal(StyleService element) {
        TextField tf = new TextField();
        tf.setId("textfield_normal");
        tf.setBackground(new Background(new BackgroundFill(element.getBackgroudColor_back(), CornerRadii.EMPTY, Insets.EMPTY)));
        tf.getStylesheets().add("textfield_normal");
        return tf;
    }

    @Override
    public TextField createGreat(StyleService element) {
        TextField tf = new TextField();
        tf.setId("textfield_great");
        tf.setBackground(new Background(new BackgroundFill(element.getBackgroudColor_front(), CornerRadii.EMPTY, Insets.EMPTY)));
        tf.getStylesheets().add("textfield_great");
        return tf;
    }
}
