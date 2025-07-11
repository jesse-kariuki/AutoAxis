module com.example.autoaxis {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires java.sql;
    requires java.desktop;

    opens com.example.autoaxis to javafx.fxml;
    exports com.example.autoaxis;
    exports com.example.autoaxis.controllers;
    opens com.example.autoaxis.controllers to javafx.fxml;
    exports com.example.autoaxis.dto;
    opens com.example.autoaxis.dto to javafx.base;
    opens com.example.autoaxis.entities to javafx.base;

}