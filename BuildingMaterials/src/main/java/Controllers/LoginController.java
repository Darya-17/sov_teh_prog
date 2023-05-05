package Controllers;

import Models.User;
import Services.APIService;
import Services.EncryptService;
import Utils.Utils;
import com.example.buildingmaterials.BuildingMaterialsApplication;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import Utils.JsonUtils;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class LoginController extends BaseViewController {
    public Button authBtn;
    public Label errorLabel;
    @Override
    protected String getTitle() {
        return "Вход";
    }
    public TextField loginField;
    public TextField passField;
    public Button registrationBtn;
    private void setError(String errorText) {
        errorLabel.setText(errorText);
    }

    public void onRegistrationBtnClick(MouseEvent event) throws IOException {
        ScreenController.instance.activate("registration");
    }
    public void onAuthBtnClick(MouseEvent event) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, IOException {
        var login = loginField.getText();
        var pass = passField.getText();
        var encryptedPass = EncryptService.encryptPassword(pass);
        var responseObject = APIService.auth(login, encryptedPass);
        var errorMsg = Utils.getMessageFromResponse(responseObject);
        if (errorMsg != null) {
            setError(errorMsg);
            return;
        }
        if (responseObject != null) {
            var dataUser = responseObject.get_responseInfo().get("data").asText();
            BuildingMaterialsApplication.currentUser = JsonUtils.Instance.deserialize(dataUser, User.class);
            ScreenController.instance.activate("main");
            return;
        }
        setError("Нет соединения с сервером!");

    }
}
