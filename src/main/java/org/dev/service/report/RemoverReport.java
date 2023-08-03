package org.dev.service.report;

import org.dev.model.ReportModel;
import org.dev.model.dao.HibernateReportDAO;
import org.dev.service.MyServicesDAO;
import org.dev.util.contexto.EntityManagerContexto;
import org.dev.util.menssagensInternas.GenericMenssage;

public class RemoverReport extends MyServicesDAO {
    private final ReportModel reportModel;

    public RemoverReport(ReportModel reportModel) {
        this.reportModel = reportModel;
    }

    @Override
    public GenericMenssage<Boolean, String> execute() {
        try{
            new HibernateReportDAO(EntityManagerContexto.getInstance().getContexto()).delete(reportModel);
            return new GenericMenssage<>(true, "Report removido com sucesso");
        }catch (Exception e){
            return new GenericMenssage<>(false,e.getMessage());
        }
    }
}
