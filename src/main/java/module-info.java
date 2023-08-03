module programas.uteis {
    requires javafx.controls;
    requires javafx.fxml;
    requires jakarta.persistence;
    requires org.hibernate.orm.core;
    requires com.hierynomus.smbj;
    requires java.xml.bind;
    requires java.desktop;

    opens org.dev.init;
    opens org.dev.model;
    opens org.dev.control;
    opens org.dev.util;
    opens org.dev.service;
    opens org.dev.util.contexto;
    opens org.dev.util.tarefas;
    exports org.dev.init;

}