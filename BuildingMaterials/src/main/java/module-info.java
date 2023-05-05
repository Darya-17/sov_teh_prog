module com.example.buildingmaterials {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.annotation;
    requires com.fasterxml.jackson.datatype.jsr310;
    requires com.fasterxml.jackson.databind;
    requires org.kordamp.bootstrapfx.core;
    requires javafx.graphics;
    exports com.example.buildingmaterials;
    exports Controllers;
    exports Models;
    exports Controls;
}