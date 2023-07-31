package org.dev.control.service;

import org.dev.model.LinksModel;

public class ReportEditavel {
    private LinksModel report;

    private static ReportEditavel instance;

    private ReportEditavel(){}

    public static ReportEditavel getInstance() {
        if(instance==null){
            instance = new ReportEditavel();
        }
        return instance;
    }

    public LinksModel getReport() {
        return report;
    }

    public void setReport(LinksModel reportEditavel){
        this.report = reportEditavel;
    }
}
