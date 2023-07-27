package org.dev.control.factorys.fxFactory;

import javafx.scene.control.Label;
import org.dev.control.factorys.AbstractFactory;
import org.dev.control.service.StyleService;

public class LabelFactory implements AbstractFactory<Label, StyleService>  {
    @Override
    public Label createBasic(StyleService element) {
        Label label = new Label();
        label.setFont(element.getFont_Text());
        label.setTextFill(element.getText());
        return label;
    }

    @Override
    public Label createNormal(StyleService element) {
        Label label = new Label();
        label.setFont(element.getFont_subtitle());
        label.setTextFill(element.getText());
        return label;
    }

    @Override
    public Label createGreat(StyleService element) {
        Label label = new Label();
        label.setFont(element.getFont_title());
        label.setTextFill(element.getText());
        return label;
    }
}
