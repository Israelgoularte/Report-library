package org.dev.control.service;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public enum StyleEnum {
    DEFAULT(Color.BLACK, Color.WHITE, Color.GRAY, Color.LIGHTGRAY, null, Color.RED, Color.GREEN, Color.BLUE, Font.font("Arial",24), Font.font("Arial", 18), Font.font("Arial", 14)),
    DARK(Color.WHITE, Color.BLACK, Color.GRAY, Color.LIGHTGRAY, null, Color.YELLOW, Color.MAGENTA, Color.CYAN, Font.font("Arial", 24),Font.font("Arial", 18),Font.font("Arial", 14)),
    LIGHT(Color.BLACK, Color.WHITE, Color.GRAY, Color.LIGHTGRAY, null, Color.PINK, Color.ORANGE, Color.PURPLE, Font.font("Arial", 24), Font.font("Arial", 18), Font.font("Arial", 14));

    private StyleService style;

    private StyleEnum(Color text,
                      Color backgroudColor_back,
                      Color backgroudColor_mid,
                      Color backgroudColor_front,
                      Image backgroudImage,
                      Color custom_one,
                      Color custom_sec,
                      Color custom_tre, Font font_title, Font font_subtitle, Font font_text) {
        style = new StyleService(text, backgroudColor_back, backgroudColor_mid, backgroudColor_front, backgroudImage, custom_one, custom_sec, custom_tre,font_title,font_subtitle,font_text);
    }

    public StyleService getStyle() {
        return style;
    }
}
