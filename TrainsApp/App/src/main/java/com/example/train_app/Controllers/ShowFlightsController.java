package com.example.train_app.Controllers;

import com.example.train_app.Models.Flight;
import com.example.train_app.Services.APIService;
import javafx.beans.value.ObservableValueBase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

public class ShowFlightsController {

    public TableColumn<Flight, Integer> PriceCol;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button mainAddFlight;

    @FXML
    private Button mainAddTrain;

    @FXML
    private TextField mainCityFrom;

    @FXML
    private TextField mainCityWhere;
    @FXML
    private TableView<Flight> mainTable;
    @FXML
    private TableColumn<Flight, String> mainTableArr;

    @FXML
    private TableColumn<Flight, String> mainTableDeparture;
    @FXML
    private TableColumn<Flight, String> mainTableFrom;
    @FXML
    private TableColumn<Flight, Integer> mainTableSeats;
    @FXML
    private TableColumn<Flight, String> mainTableWhere;
    @FXML
    private TableColumn<Flight, String> mainTableTrainName;
    @FXML
    private Button mainTrains;
    @FXML
    private ComboBox<?> todoCategory;
    private ObservableList<Flight> flights = FXCollections.observableArrayList();;
    @FXML
    void initialize() throws Exception {
        showData();

        mainTrains.setOnAction(x ->{
            try {
                ScreenController.instance.activateScreen("trains");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        mainAddTrain.setOnAction(x ->{
            try {
                ScreenController.instance.activateScreen("addTrain");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        mainAddFlight.setOnAction(x ->{
            try {
                ScreenController.instance.activateScreen("addFlight");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }



    public void showData() throws Exception {
        flights.clear();
        flights = FXCollections.observableArrayList(APIService.getFlights());
        mainTableArr.setCellValueFactory(new PropertyValueFactory<>("arrivalDateTime"));
        mainTableDeparture.setCellValueFactory(new PropertyValueFactory<>("departureDateTime"));
        mainTableFrom.setCellValueFactory(new PropertyValueFactory<>("cityFrom"));
        mainTableSeats.setCellValueFactory(cellData ->
                new ObservableValueBase<>() {
                    @Override
                    public Integer getValue() {
                        return Arrays.stream(cellData.getValue().getTrain().getCarriages())
                                .mapToInt(it -> it.getSeats_count()).sum();
                    }
                });
        mainTableWhere.setCellValueFactory(new PropertyValueFactory<>("cityWhere"));
        PriceCol.setCellValueFactory(new PropertyValueFactory<>("basePrice"));
        mainTableTrainName.setCellValueFactory(cellData -> new ObservableValueBase<String>() {
            @Override
            public String getValue() {
                return cellData.getValue().getTrain().getName();
            }
        } );
        mainTable.setItems(flights);
    }


}
