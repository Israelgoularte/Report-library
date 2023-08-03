package org.dev.util;

import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Menu;
import javafx.scene.layout.HBox;
import org.dev.service.fxelement.MenuReportOptionsMyServices;
import org.dev.service.fxelement.ReportChoiceOptions;
import org.dev.util.menssagensInternas.GenericMenssage;

public class TipoAndCategoriaChoiceLoad {

    public static void options(HBox tipoBox, ChoiceBox<String> tipoChoice, HBox categoriaBox, ChoiceBox<String> categoriaChoice, Menu reportMenu) {
        GenericMenssage<Boolean,String> tipoChoiceResult = new ReportChoiceOptions
                (tipoBox, tipoChoice,"tipo")
                .execute();
        if (tipoChoiceResult.getMenssageOne()){
            //trativa de erro
        }

        GenericMenssage<Boolean,String> categoriaChoiceResult = new ReportChoiceOptions
                (categoriaBox, categoriaChoice,"categoria")
                .execute();
        if (categoriaChoiceResult.getMenssageOne()){
            //trativa de erro
        }

        GenericMenssage<Boolean,String> addMenuReportOptionsResult = new MenuReportOptionsMyServices(reportMenu).execute();
        if (addMenuReportOptionsResult.getMenssageOne()){
            //tratativa de erro;
        }
    }
}
