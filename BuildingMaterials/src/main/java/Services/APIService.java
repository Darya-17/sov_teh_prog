package Services;

import Models.*;
import Utils.JsonUtils;
import Utils.Utils;
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

    final private static String port = "8080";
    private static ResponseObject SendHTTPRequest(String url, String method, ObjectNode data) throws IOException {
        var obj = new URL(MessageFormat.format("http://localhost:{0}/{1}", port, url));
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

    public static ResponseObject auth(String login, String password) throws IOException {
        var jObject = JsonUtils.Instance.createJObject();
        jObject.put("login", login);
        jObject.put("password", password);
        var responseObj = SendHTTPRequest("users/auth", "POST", jObject);
        return responseObj;

    }

    public static String register(User user) throws IOException {
        var data = JsonUtils.Instance.deserialize(JsonUtils.Instance.serializeObject(user), ObjectNode.class);
        var responseObj = SendHTTPRequest("users/register", "POST", data);
        return Utils.getMessageFromResponse(responseObj);
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

    public static Material[] getMaterials() throws IOException {
        return getObjectsAll("materials/all", Material[].class);

    }

    public static Category[] getCategories() throws IOException {
        return getObjectsAll("categories/all", Category[].class);
    }

    public static RequestType[] getRequestTypes() throws IOException {
        return getObjectsAll("request-types/all", RequestType[].class);
    }

    public static Request[] getRequests() throws IOException {
        return getObjectsAll("requests/all", Request[].class);
    }

    public static Role[] getRoles() throws IOException {
        return getObjectsAll("roles/all", Role[].class);

    }

    public static Stock[] getStocks() throws IOException {
        return getObjectsAll("stocks/all", Stock[].class);
    }

    public static ResponseObject HandleRequest(int requestId) throws IOException {
        var response = SendHTTPRequest("requests/handle/" + requestId, "POST", null);
        return response;
    }

    public static ResponseObject SendNewRequest(Request request) throws IOException {
        var data = JsonUtils.Instance.deserialize(JsonUtils.Instance.serializeObject(request), ObjectNode.class);
        return SendHTTPRequest("requests/add", "POST", data);
    }

}
