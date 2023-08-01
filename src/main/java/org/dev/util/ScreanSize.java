package util;

import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;

/**
 * Classe para obter informações sobre o tamanho da tela (ScreanSize).
 */
public class ScreanSize {
    private double width;
    private double height;

    private static ScreanSize instance;

    private ScreanSize() {
        Rectangle2D screen = Screen.getPrimary().getBounds();
        this.width = screen.getWidth();
        this.height = screen.getHeight()-40;
    }

    /**
     * Obtém a instância do tamanho da tela.
     *
     * @return a instância do tamanho da tela
     */
    public static ScreanSize getInstance() {
        if (instance == null) {
            instance = new ScreanSize();
        }
        return instance;
    }

    /**
     * Obtém a largura da tela.
     *
     * @return a largura da tela
     */
    public double getWidth() {
        return width;
    }

    /**
     * Obtém a altura da tela.
     *
     * @return a altura da tela
     */
    public double getHeight() {
        return height;
    }
}
