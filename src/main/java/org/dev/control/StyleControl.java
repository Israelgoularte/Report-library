package org.dev.control;

import org.dev.control.service.StyleEnum;
import org.dev.control.service.StyleService;

public class StyleControl {
    private static StyleControl instance;
    public static StyleControl getInstance() {
        if(instance == null){
            instance = new StyleControl();
        }
        return instance;
    }

    private StyleService style;

    private StyleControl(){
        style = StyleEnum.DEFAULT.getStyle();
    }

    public void setStyle(StyleService style) {
        this.style = style;
    }

    public StyleService getStyle() {
        return style;
    }
}
