package org.dev.control.factorys.fxFactory;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import org.dev.control.factorys.AbstractFactory;
import org.dev.control.service.StyleService;

public class ButtonFactory implements AbstractFactory<Button, StyleService> {

    @Override
    public Button createBasic(StyleService element) {
        Button btn = new Button();
        btn.setId("button_basic");
        btn.setBackground(new Background(new BackgroundFill(element.getBackgroudColor_mid(), CornerRadii.EMPTY, Insets.EMPTY)));
        btn.setFont(element.getFont_Text());
        btn.setTextFill(element.getText());
        return btn;
    }

    @Override
    public Button createNormal(StyleService element) {
        Button btn = new Button();
        btn.setId("button_normal");
        btn.setBackground(new Background(new BackgroundFill(element.getBackgroudColor_back(), CornerRadii.EMPTY, Insets.EMPTY)));
        btn.setFont(element.getFont_Text());
        btn.setTextFill(element.getText());
        return btn;
    }

    @Override
    public Button createGreat(StyleService element) {
        Button btn = new Button();
        btn.setId("button_great");
        btn.setBackground(new Background(new BackgroundFill(element.getBackgroudColor_front(), CornerRadii.EMPTY, Insets.EMPTY)));
        btn.setFont(element.getFont_Text());
        btn.setTextFill(element.getText());
        return btn;
    }
}
