package org.dev.control.service;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class StyleService {
    private Color text;
    private Color backgroudColor_back;
    private Color backgroudColor_mid;
    private Color backgroudColor_front;
    private Image backgroudImage;
    private Color custom_one;
    private Color custom_sec;
    private Color custom_tre;

    private Font font_title;

    private Font font_subtitle;

    private Font font_Text;

    public StyleService(){
    }

    public StyleService(Color text,
                        Color backgroudColor_back,
                        Color backgroudColor_mid,
                        Color backgroudColor_front,
                        Image backgroudImage,
                        Color custom_one,
                        Color custom_sec,
                        Color custom_tre,
                        Font font_title,
                        Font font_subtitle,
                        Font font_Text) {
        this.text = text;
        this.backgroudColor_back = backgroudColor_back;
        this.backgroudColor_mid = backgroudColor_mid;
        this.backgroudColor_front = backgroudColor_front;
        this.backgroudImage = backgroudImage;
        this.custom_one = custom_one;
        this.custom_sec = custom_sec;
        this.custom_tre = custom_tre;
        this.font_title = font_title;
        this.font_subtitle = font_subtitle;
        this.font_Text = font_Text;
    }

    public Color getText() {
        return text;
    }

    public void setText(Color text) {
        this.text = text;
    }

    public Color getBackgroudColor_back() {
        return backgroudColor_back;
    }

    public void setBackgroudColor_back(Color backgroudColor_back) {
        this.backgroudColor_back = backgroudColor_back;
    }

    public Color getBackgroudColor_mid() {
        return backgroudColor_mid;
    }

    public void setBackgroudColor_mid(Color backgroudColor_mid) {
        this.backgroudColor_mid = backgroudColor_mid;
    }

    public Color getBackgroudColor_front() {
        return backgroudColor_front;
    }

    public void setBackgroudColor_front(Color backgroudColor_front) {
        this.backgroudColor_front = backgroudColor_front;
    }

    public Image getBackgroudImage() {
        return backgroudImage;
    }

    public void setBackgroudImage(Image backgroudImage) {
        this.backgroudImage = backgroudImage;
    }

    public Color getCustom_one() {
        return custom_one;
    }

    public void setCustom_one(Color custom_one) {
        this.custom_one = custom_one;
    }

    public Color getCustom_sec() {
        return custom_sec;
    }

    public void setCustom_sec(Color custom_sec) {
        this.custom_sec = custom_sec;
    }

    public Color getCustom_tre() {
        return custom_tre;
    }

    public void setCustom_tre(Color custom_tre) {
        this.custom_tre = custom_tre;
    }

    public Font getFont_title() {
        return font_title;
    }

    public void setFont_title(Font font_title) {
        this.font_title = font_title;
    }

    public Font getFont_subtitle() {
        return font_subtitle;
    }

    public void setFont_subtitle(Font font_subtitle) {
        this.font_subtitle = font_subtitle;
    }

    public Font getFont_Text() {
        return font_Text;
    }

    public void setFont_Text(Font font_Text) {
        this.font_Text = font_Text;
    }
}
