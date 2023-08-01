module programas.uteis {
    requires javafx.controls;
    requires javafx.fxml;
    requires jakarta.persistence;
    requires org.hibernate.orm.core;
    requires com.hierynomus.smbj;
    requires java.xml.bind;
    requires java.desktop;

    opens org.dev.init to javafx.fxml;
    opens org.dev.model;
    opens org.dev.control;
    opens org.dev.util;


    exports org.dev.init;
    opens org.dev.control.repository;
    opens org.dev.control.service;
    opens org.dev.control.service.boxCreators;

}