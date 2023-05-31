module com.example.trainfront {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.net.http;
    requires json;
    requires org.apache.httpcomponents.httpclient;
    requires org.apache.httpcomponents.httpcore;
    requires com.fasterxml.jackson.databind;
    requires java.sql;
    requires com.fasterxml.jackson.datatype.jsr310;

    opens com.example.train_app to javafx.fxml;
    exports com.example.train_app;
    exports com.example.train_app.Controllers;
    opens com.example.train_app.Controllers to javafx.fxml;
    exports com.example.train_app.Models;
    exports com.example.train_app.Controls;

}