package com.example.train_app.Services;

import com.example.train_app.Models.Flight;
import com.example.train_app.Models.ResponseObject;
import com.example.train_app.Models.Train;
import com.example.train_app.Models.TrainType;
import com.example.train_app.Utils.JsonUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.MessageFormat;

public class APIService {

    private static String ip = "localhost";
    private static String port = "8080";

    private static ResponseObject SendHTTPRequest(String url, String method, ObjectNode data) throws IOException {
        var obj = new URL(MessageFormat.format("http://{0}:{1}/{2}", ip, port, url));
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod(method);
        con.setRequestProperty("Content-type", "application/json");
        con.setDoOutput(true);
        int responseCode;
        try {
            if (data != null) {
                OutputStream os;
                os = con.getOutputStream();
                os.write(data.toString().getBytes());
                os.flush();
                os.close();
            }
            responseCode = con.getResponseCode();
        } catch (IOException e) {
            return null;
        }
        ObjectNode jObject = null;
        if (responseCode == HttpURLConnection.HTTP_OK) { //success
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            try {
                jObject = JsonUtils.Instance.deserialize(response.toString(), ObjectNode.class);
            } catch (JsonProcessingException ignored) {
            }
        }
        return new ResponseObject(responseCode, jObject);
    }

    private static <T> T getObjectsAll(String url, Class<T> valueType) throws IOException {
        var response = SendHTTPRequest(url, "GET", null);
        if (response == null) {
            AlertService.ShowAlert("Ошибка", "Ошибка", "Ошибка соединения с сервером!");
            return null;
        }
        var data = response.get_responseInfo().get("data");
        return JsonUtils.Instance.deserialize(data.asText(), valueType);
    }

    public static TrainType[] getTrainTypes() throws IOException {
        return getObjectsAll("getTrainTypes", TrainType[].class);
    }

    public static Train[] getTrains() throws IOException {
        return getObjectsAll("getallTrains", Train[].class);
    }

    public static Flight[] getFlights() throws IOException {
        return getObjectsAll("getallFlights", Flight[].class);
    }

    public static void addTrain(Train train) throws IOException {
        var response = SendHTTPRequest("addTrain", "POST",
                JsonUtils.Instance.getObjectNodeFromObject(train));
        AlertService.ShowAlert("Успех", "Поезд", "Поезд успешно добавлен!");
    }

    public static void addFlight(Flight flight) throws IOException {
        var response = SendHTTPRequest("addFlight", "POST",
                JsonUtils.Instance.getObjectNodeFromObject(flight));
        AlertService.ShowAlert("Успех", "Полет", "Полет успешно добавлен!");
    }
}
