package com.example.train_app.Controllers;

import com.example.train_app.Models.Carriage;
import com.example.train_app.Models.Train;
import com.example.train_app.Models.TrainType;
import com.example.train_app.Services.APIService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.util.converter.IntegerStringConverter;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AddTrainController {

    public TableView<Carriage> carriagesTable;
    public TableColumn<Carriage, Integer> seats_count_column;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    @FXML
    private Button addTrainAddFlights;
    @FXML
    private Button addTrainFlights;
    @FXML
    private Button addTrainTrains;
    @FXML
    private TextField addTrainName;

    @FXML
    private TextField addTrainSeats;
    @FXML
    private TextField addTrainCarriage;
    @FXML
    private ComboBox<TrainType> addTrainType;
    private ObservableList<TrainType> trainTypesData = FXCollections.observableArrayList();
    private ObservableList<Carriage> carriages = FXCollections.observableArrayList();

    @FXML
    private Button addTrainSave;

    @FXML
    void initialize() throws IOException {
        carriagesTable.setItems(carriages);
        seats_count_column.setCellValueFactory(new PropertyValueFactory<Carriage, Integer>("seats_count"));
        seats_count_column.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));

        seats_count_column.setOnEditCommit(
                (TableColumn.CellEditEvent<Carriage, Integer> t) ->
                        (t.getTableView().getItems().get(
                                t.getTablePosition().getRow())
                        ).setSeats_count(t.getNewValue())
        );
        trainTypesData = FXCollections.observableArrayList(APIService.getTrainTypes());
        addTrainType.setItems(trainTypesData);
        addTrainSave.setOnAction(x -> {
            String trainName = addTrainName.getText();
            TrainType trainType = addTrainType.getValue();
            try {
                sendPostTrain(trainName, trainType);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        addTrainTrains.setOnAction(x -> {
            try {
                ScreenController.instance.activateScreen("trains");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        addTrainFlights.setOnAction(x -> {
            try {
                ScreenController.instance.activateScreen("flights");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        addTrainAddFlights.setOnAction(x -> {
            try {
                ScreenController.instance.activateScreen("addFlight");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

    }

    private void sendPostTrain(String name, TrainType type) throws Exception {

        var train = new Train();
        train.setName(name);
        train.setTrainType(type);
        train.setCarriages(carriages.stream().toArray(it -> new Carriage[it]));
        train.setName(name);
        APIService.addTrain(train);

    }

    public void addCarriage(MouseEvent event) {
        var a = new Carriage(0);
        carriages.add(a);
        carriagesTable.refresh();
    }
}



