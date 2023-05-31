package com.example.train_app.Controllers;

import com.example.train_app.Models.Flight;
import com.example.train_app.Models.Train;
import com.example.train_app.Services.APIService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AddFlightController {

    @FXML
    private Button addButtonFlights;

    @FXML
    private Button addButtonTrains;

    @FXML
    private Button addButtonAddTrain;

    @FXML
    private DatePicker addArrDateTime;


    @FXML
    private DatePicker addDepDateTime;

    @FXML
    private TextField addFrom;

    @FXML
    private Button addSave;

    @FXML
    private TextField addBasicPrice;

    @FXML
    private TextField addWhere;
    @FXML
    private ComboBox<Train> TrainCombo;
    private ObservableList<Train> myComboBoxData = FXCollections.observableArrayList();

    @FXML
    void initialize() throws Exception {

        var trains = FXCollections.observableArrayList(APIService.getTrains());
        myComboBoxData.addAll(trains);
        TrainCombo.setItems(myComboBoxData);
        addSave.setOnAction(x -> {
            var flight = new Flight();
            flight.setCityFrom(addFrom.getText());
            flight.setCityWhere(addWhere.getText());
            flight.setArrivalDateTime(addArrDateTime.getValue());
            flight.setDepartureDateTime(addDepDateTime.getValue());
            flight.setBasePrice(Integer.valueOf(addBasicPrice.getText()));
            flight.setTrain(TrainCombo.getValue());
            try {
                APIService.addFlight(flight);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        addButtonTrains.setOnAction(x -> {
            try {
                ScreenController.instance.activateScreen("trains");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        addButtonAddTrain.setOnAction(x -> {
            try {
                ScreenController.instance.activateScreen("addTrain");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        addButtonFlights.setOnAction(x -> {
            try {
                ScreenController.instance.activateScreen("flights");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

    }


    private void sendPostFlight(String cityFrom, String cityWhere, String departureDate, String arrivalDate, String departureTime, String arrivalTime, String basePrice, String train) throws Exception {
        // add request parameter, form parameters
        List<NameValuePair> urlParameters = new ArrayList<>();
        urlParameters.add(new BasicNameValuePair("cityFrom", cityFrom));
        urlParameters.add(new BasicNameValuePair("cityWhere", cityWhere));
        urlParameters.add(new BasicNameValuePair("departureDate", departureDate));
        urlParameters.add(new BasicNameValuePair("arrivalDate", arrivalDate));
        urlParameters.add(new BasicNameValuePair("departureTime", departureTime));
        urlParameters.add(new BasicNameValuePair("arrivalTime", arrivalTime));
        urlParameters.add(new BasicNameValuePair("basePrice", basePrice));
        urlParameters.add(new BasicNameValuePair("train", train));

        HttpPost post = new HttpPost("http://localhost:8080/addFlight");

        post.setEntity(new UrlEncodedFormEntity(urlParameters));


        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(post)) {
            System.out.println(EntityUtils.toString(response.getEntity()));
        }
    }

}