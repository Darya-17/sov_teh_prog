package Controllers;

import Models.Role;
import Models.User;
import Services.APIService;
import Services.AlertService;
import Services.EncryptService;
import Utils.Utils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.net.URL;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;
import java.util.ResourceBundle;

public class RegistrationController extends BaseViewController implements Initializable {
    public TextField loginField;
    public TextField firstPassField;
    public TextField repeatPassField;
    public Label errorLabel;
    public Button backBtn;
    public ComboBox roleCombo;
    private ObservableList<Role> roles;

    private void setError(String errorText){
        errorLabel.setText(errorText);
    }
    public void onRegisterClicked(MouseEvent event) throws IOException, IllegalBlockSizeException, NoSuchPaddingException, BadPaddingException, NoSuchAlgorithmException, InvalidKeyException {
        var login = loginField.getText();
        var firstPass = firstPassField.getText();
        var repeatPass = repeatPassField.getText();
        if (Utils.CheckStringEmpty(login)){
            setError("Не заполнено поле логин!");
            return;
        }
        if (Utils.CheckStringEmpty(firstPass)){
            setError("Не заполнено поле пароль!");
            return;
        }
        if (Utils.CheckStringEmpty(repeatPass)){
            setError("Не заполнено поле повторите пароль!");
            return;
        }
        if (!Objects.equals(firstPass, repeatPass)){
            setError("Пароли не соответствуют!");
            return;
        }
        var encryptedPass = EncryptService.encryptPassword(firstPass);
        var user = new User();
        user.setLogin(login);
        user.setPassword(encryptedPass);
        user.setRole((Role) roleCombo.getValue());
        var errorMsg = APIService.register(user);
        if (errorMsg != null){
            setError(errorMsg);
            return;
        }
        AlertService.ShowAlert("Сообщение","Успешно", "Вы успешно зарегистрировались!");
        ScreenController.instance.activate("login");

    }

    public void onBackClicked(MouseEvent event) throws IOException {
        var result = AlertService.ShowAlertWithQuestion("Выход",  "Отмена", "Вы уверены, что хотите прервать регистрацию?");
        if (result)
            ScreenController.instance.activateScreen("login");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            roles = FXCollections.observableArrayList(APIService.getRoles());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        roleCombo.setItems(roles);
        roleCombo.setValue(roles.get(0));
    }
}
