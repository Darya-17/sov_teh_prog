package Controllers;

import Models.Material;
import Models.Request;
import Models.RequestType;
import Models.Stock;
import Services.APIService;
import Services.AlertService;
import Utils.Utils;
import com.example.buildingmaterials.BuildingMaterialsApplication;
import javafx.beans.value.ObservableValue;
import javafx.beans.value.ObservableValueBase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    public TableView<Request> RequestsTableView;
    public ObservableList<Request> requests;
    public TableColumn requestStatusCol;
    public TableColumn requestCountCol;
    public TableColumn requestMaterialCol;
    public TableColumn requestIdCol;
    public TableColumn requestTypeCol;
    public Button commandBtn;
    public TableView StockTableView;
    public TableColumn stockCount;
    public TableColumn stockCategory;
    public TableColumn stockName;
    public TableColumn stockId;
    private ObservableList<Stock>   stocks;

    //    public ObservableList<Stock> requestTypes;
    public void onBackBtnClicked(MouseEvent event) throws IOException {
        var result = AlertService.ShowAlertWithQuestion("Выход", "Выход", "Вы уверены, что хотите Выйти?");
        if (result)
            ScreenController.instance.activateScreen("login");
    }

    public void onFormRequestClicked(MouseEvent event) throws IOException {
        ScreenController.instance.activateScreen("request");
    }

    public void onFormHandleClicked(MouseEvent event) throws IOException {
        var request = RequestsTableView.getSelectionModel().getSelectedItem();
        if (request == null) {
            AlertService.ShowAlert("Ошибка", "Ошибка", "Вы не выбрали не одну заявку!");
            return;
        }
        if (request.isHandled()) {
            AlertService.ShowAlert("Ошибка", "Ошибка", "Данная заявка уже обработана!");
            return;

        }
        var responseObject = APIService.HandleRequest(request.getId());
        var errorMsg = Utils.getMessageFromResponse(responseObject);
        if (errorMsg != null)
            AlertService.ShowAlert("Ошибка", "Ошибка", errorMsg);
        else {
            AlertService.ShowAlert("Успех", "Успех", "Заявка успешно обработана!");
            request.setHandled(true);
            InitTableViews();
        }


    }
    private void setMode() {
        if (BuildingMaterialsApplication.currentUser.getRole().getId() == 1) {
            commandBtn.setText("Отметить заявку выполненной");
            commandBtn.setOnMouseClicked(event -> {
                try {
                    onFormHandleClicked(event);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        }
    }
    private void SetRequestsTable() throws IOException {
        requests = FXCollections.observableArrayList(APIService.getRequests());
        RequestsTableView.setItems(requests);
        requestIdCol.setCellValueFactory(new PropertyValueFactory<Request, Integer>("id"));
        requestMaterialCol.setCellValueFactory(new PropertyValueFactory<Request, Material>("material"));
        requestCountCol.setCellValueFactory(new PropertyValueFactory<Request, Integer>("count"));
        requestTypeCol.setCellValueFactory(new PropertyValueFactory<Request, RequestType>("requestType"));
        requestStatusCol.setCellValueFactory((Callback<TableColumn.CellDataFeatures, ObservableValue>) param -> {
            Request req = (Request) param.getValue();
            String result;
            if (req.isHandled())
                result = "Обработана";
            else
                result = "Не обработана";
            return new ObservableValueBase() {
                @Override
                public Object getValue() {
                    return result;
                }
            };
        });
    }

    private void SetStockTable() throws IOException {
        stocks = FXCollections.observableArrayList(APIService.getStocks());

        StockTableView.setItems(stocks);
        stockId.setCellValueFactory(new PropertyValueFactory<Stock, Integer>("id"));
        stockName.setCellValueFactory(new PropertyValueFactory<Stock, Material>("material"));
        stockCount.setCellValueFactory(new PropertyValueFactory<Stock, Integer>("count"));
        stockCategory.setCellValueFactory((Callback<TableColumn.CellDataFeatures, ObservableValue>) param -> {
            Stock stock = (Stock) param.getValue();
            return new ObservableValueBase() {
                @Override
                public Object getValue() {
                    return stock.getMaterial().getCategory();
                }
            };
        });
    }

    private void InitTableViews() throws IOException {
        SetRequestsTable();
        SetStockTable();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            InitTableViews();
            setMode();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
