package com.example.train_app.Controllers;

import com.example.train_app.Models.Train;
import com.example.train_app.Models.TrainType;
import com.example.train_app.Services.APIService;
import javafx.beans.value.ObservableStringValue;
import javafx.beans.value.ObservableValueBase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

public class ShowTrainsController {

    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;
    @FXML
    private Button trainsAddFlight;
    @FXML
    private Button trainsAddTrain;
    @FXML
    private Button trainsFlights;
    @FXML
    private TableView<Train> trainsTable;
    @FXML
    private TableColumn<Train, Integer> trainsCarriageColumn;

    @FXML
    private TableColumn<Train, String> trainsNameColumn;

    @FXML
    private TableColumn<Train, Integer> trainsSeatsColumn;

    @FXML
    private TableColumn<Train, TrainType> trainsTypeColumn;

    @FXML
    void initialize() throws Exception {


        // List<TrainType> types = new ArrayList<TrainType>(Arrays.asList(TrainType.values()));
        ObservableList<Train> trains = FXCollections.observableArrayList(APIService.getTrains());
        trainsNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        trainsTypeColumn.setCellValueFactory(new PropertyValueFactory<>("trainType"));
        trainsSeatsColumn.setCellValueFactory(cellData ->
                new ObservableValueBase<>() {
                    @Override
                    public Integer getValue() {
                        return Arrays.stream(cellData.getValue().getCarriages()).mapToInt(it -> it.getSeats_count()).sum();
                    }
                });
        trainsCarriageColumn.setCellValueFactory(cellData ->
                new ObservableValueBase<>() {
                    @Override
                    public Integer getValue() {
                        return cellData.getValue().getCarriages().length;
                    }
                });
        trainsTable.setItems(trains);

        trainsFlights.setOnAction(x -> {
            try {
                ScreenController.instance.activateScreen("flights");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        trainsAddTrain.setOnAction(x -> {
            try {
                ScreenController.instance.activateScreen("addTrain");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        trainsAddFlight.setOnAction(x -> {
            try {
                ScreenController.instance.activateScreen("addFlight");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });


    }

}



