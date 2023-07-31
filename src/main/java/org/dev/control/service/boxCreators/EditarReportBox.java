package org.dev.control.service.boxCreators;

import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.dev.model.LinksModel;

public class EditarReportBox extends Alert {
    private String[] info;

    private final String[] info_label = {"Nome","Tipo","Categoria","Descrição"};

    private LinksModel linksModel;

    public EditarReportBox(LinksModel linksModel) {
        super(AlertType.CONFIRMATION);
        this.linksModel = linksModel;
        this.info = new String[4];
        this.info[0] = linksModel.getNome();
        this.info[1] = linksModel.getTipo();
        this.info[2] = linksModel.getcategoria();
        this.info[3] = linksModel.getDescricao();
    }

    public EditarReportBox build(){
        this.setTitle("Editar Report");
        VBox main_box = new VBox();
        String rootcss = EditarReportBox.class.getResource("/view/css/editarReport.css").toExternalForm();
//        String boxcss = EditarReportBox.class.getResource("/view/css/styles/box_Style.css").toExternalForm();
//        String labelcss = EditarReportBox.class.getResource("/view/css/styles/label_Style.css").toExternalForm();
//        String fieldscss = EditarReportBox.class.getResource("/view/css/styles/texteField_Style.css").toExternalForm();

        main_box.getStylesheets().add(rootcss);
        main_box.getStyleClass().add("medium-box");

        HBox[] elements = new HBox[4];
        for (int i = 0; i < elements.length; i++) {
            elements[i] = new HBox();
            elements[i].getStyleClass().add("comum-box");
            Label lblElement = new Label(info_label[i]);

            TextField tfElement = new TextField();
            tfElement.setPromptText(info[i]);

            elements[i].getChildren().addAll(lblElement,tfElement);
            main_box.getChildren().add(elements[i]);
        }
        this.setGraphic(main_box);
        return this;
    }




}
