package Controllers;

import Controls.NumberTextField;
import Models.Material;
import Models.Request;
import Models.RequestType;
import Models.User;
import Services.APIService;
import Services.AlertService;
import Utils.Utils;
import com.example.buildingmaterials.BuildingMaterialsApplication;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import Utils.JsonUtils;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RequestController implements Initializable {
    public ComboBox<RequestType> comboRequestType;
    public ObservableList<Material> materials;
    public ObservableList<RequestType> requestTypes;
    public ComboBox<Material> comboMaterial;
    public NumberTextField countField;
    public Label errorLabel;

    public RequestController() throws IOException {

    }

    public void onBackClicked(MouseEvent event) throws IOException {
        var result = AlertService.ShowAlertWithQuestion("Отмена", "Отмена", "Вы уверены, что хотите отменить создание заявки?");
        if (result)
            ScreenController.instance.activateScreen("main");
    }

    private void initCombos() throws IOException {
        materials = FXCollections.observableArrayList(APIService.getMaterials());
        requestTypes = FXCollections.observableArrayList(APIService.getRequestTypes());
        comboMaterial.setItems(materials);
        comboRequestType.setItems(requestTypes);
        comboMaterial.setValue(materials.get(0));
        comboRequestType.setValue(requestTypes.get(0));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            initCombos();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void setError(String errorText) {
        errorLabel.setText(errorText);
    }

    public void onRequestClicked(MouseEvent event) throws IOException {
        var countValue = countField.textProperty().getValue();
        int count = 0;
        if (!countValue.isEmpty()) {
            count = Integer.parseInt(countField.textProperty().getValue());
            if (count == 0) {
                AlertService.ShowAlert("Ошибка", "Ошибка", "Количество не может быть равным 0!");
                return;
            }
        }
        else{
            AlertService.ShowAlert("Ошибка", "Ошибка", "Количество не может быть равным 0!");
            return;
        }
        var material = comboMaterial.valueProperty().getValue();
        var requestType = comboRequestType.valueProperty().getValue();
        var request = new Request();
        request.setMaterial(material);
        request.setCount(count);
        request.setHandled(false);
        request.setUser(BuildingMaterialsApplication.currentUser);
        request.setRequestType(requestType);
        var responseObject = APIService.SendNewRequest(request);
        if (responseObject != null) {
            var errorMsg = Utils.getMessageFromResponse(responseObject);
            if (errorMsg != null) {
                setError(errorMsg);
                return;
            }
            AlertService.ShowAlert("Успех", "Заявка", "Заявка успешно создана!");
            ScreenController.instance.activate("main");
        }
    }
}
