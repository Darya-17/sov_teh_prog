package Utils;

import Models.ResponseObject;

import java.text.MessageFormat;
import java.util.Objects;

public class Utils {

    public static boolean CheckStringEmpty(String text) {
        return text.isEmpty() || text.trim().length() == 0;
    }

    public static String getMessageFromResponse(ResponseObject responseObj) {
        if (responseObj == null)
            return "Нет соединения с сервером! Повторите позднее";
        var responseCode = responseObj.get_code();
        String responseMsg;
        switch (responseCode) {
            case 200 -> {
                var responseInfo = responseObj.get_responseInfo();
                if (responseInfo != null)
                    if (Objects.equals(responseInfo.get("type").asText(), "ERROR"))
                        return responseInfo.get("info").asText();
                return null;
            }
            case 500 -> responseMsg = "Ошибка на сервере!, повторите позднее";
            default -> responseMsg = "Неизвестная ошибка, повторите позднее.";
        }
        responseMsg += MessageFormat.format(" Код ошибки: {0}", responseCode);
        return responseMsg;
    }

}
