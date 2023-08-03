package org.dev.service.fxelement;

import javafx.scene.layout.VBox;
import org.dev.model.ReportModel;
import org.dev.service.MyServicesDAO;
import org.dev.util.contexto.UsuarioContexto;
import org.dev.util.menssagensInternas.GenericMenssage;

public class HomeReportLoadMyServices extends MyServicesDAO {
    private final VBox homeReportBox;

    public HomeReportLoadMyServices(VBox homeReportBox) {
        this.homeReportBox = homeReportBox;
    }

    @Override
    public GenericMenssage<Boolean, String> execute() {
        try{
            HomeReportCardCreator cardCreator = new HomeReportCardCreator();
            homeReportBox.getChildren().clear();
            for (ReportModel reportModel :
                    UsuarioContexto.getInstance().getContexto().getReportsByIdUsuario()) {
                cardCreator.execute(reportModel,homeReportBox);
            }
            return new GenericMenssage<>(true,"Box gerado com sucesso");
        }catch (Exception e){
            return new GenericMenssage<>(false,e.getMessage());
        }
    }
}
