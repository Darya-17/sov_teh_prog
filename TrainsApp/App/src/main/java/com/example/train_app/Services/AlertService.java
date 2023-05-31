package com.example.train_app.Services;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class AlertService {
    public static void ShowAlert(String title, String header, String msg){
        var alert = new Alert(Alert.AlertType.INFORMATION,msg);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.showAndWait();
    }

    public static boolean ShowAlertWithQuestion(String title, String header, String msg){
        var alert = new Alert(Alert.AlertType.INFORMATION,msg, ButtonType.OK, ButtonType.CANCEL);
        alert.setTitle(title);
        alert.setHeaderText(header);
        var result = alert.showAndWait();
        return result.get().getButtonData().isDefaultButton();
    }
}
