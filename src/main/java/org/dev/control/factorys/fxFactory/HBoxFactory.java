package org.dev.control.factorys.fxFactory;

import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import org.dev.control.factorys.AbstractFactory;
import org.dev.control.service.StyleService;

public class HBoxFactory implements AbstractFactory<HBox, StyleService> {
    @Override
    public HBox create(StyleService style) {
        HBox hbox = new HBox();
        hbox.setId("hbox_basic");
        hbox.setSpacing(10);
        hbox.setPadding(new Insets(5));
        HBox.setMargin(hbox,new Insets(5));
        hbox.setBackground(new Background(new BackgroundFill(style.getBackgroudColor_front(), CornerRadii.EMPTY,Insets.EMPTY)));
        return hbox;
    }
}
