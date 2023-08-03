package org.dev.util.contexto;

import org.dev.model.ReportModel;

public class ReportContexto extends Contexto<ReportModel> {
    private static ReportContexto reportModelContexto;

    public static ReportContexto getInstance() {
        if (reportModelContexto == null)
            reportModelContexto = new ReportContexto();
        return reportModelContexto;
    }
}
