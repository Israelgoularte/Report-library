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
    public HBox createBasic(StyleService style) {
        HBox hbox = new HBox();
        hbox.setId("hbox_basic");
        hbox.setSpacing(10);
        hbox.setPadding(new Insets(5));
        HBox.setMargin(hbox,new Insets(5));
        hbox.setBackground(new Background(new BackgroundFill(style.getBackgroudColor_front(),new CornerRadii(10),new Insets(5))));
        return hbox;
    }

    @Override
    public HBox createNormal(StyleService style) {
        HBox hbox = new HBox();
        hbox.setId("hbox_normal");
        hbox.setSpacing(10);
        hbox.setPadding(new Insets(5));
        HBox.setMargin(hbox,new Insets(5));
        hbox.setBackground(new Background(new BackgroundFill(style.getBackgroudColor_mid(), CornerRadii.EMPTY,Insets.EMPTY)));
        return hbox;
    }

    @Override
    public HBox createGreat(StyleService style) {
        HBox hbox = new HBox();
        hbox.setId("hbox_great");
        hbox.setSpacing(10);
        hbox.setPadding(new Insets(10));
        hbox.setBackground(new Background(new BackgroundFill(style.getBackgroudColor_back(), CornerRadii.EMPTY,Insets.EMPTY)));
        return hbox;
    }
}
